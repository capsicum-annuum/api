package br.com.annuum.capsicum.api.security;

import br.com.annuum.capsicum.api.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import static br.com.annuum.capsicum.api.utils.DateUtils.toDate;
import static java.time.LocalDateTime.now;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${annuum.security.jwt.secret}")
    private String jwtSecret;

    @Value("${annuum.security.jwt.expiration}")
    private int jwtExpiration;

    @Autowired
    private JwtClaimMapper claimMapper;

    @Autowired
    private CustomUserDetailsService service;

    public String generateToken(final Authentication authentication) {

        log.info("Initializing JWT Token generation... ");
        final UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        log.info("UserPrincipal from authentication: {}", userPrincipal);
        return generate(userPrincipal);
    }

    private String generate(final User user) {

        log.info("Generating JWT Token... ");
        final LocalDateTime expiration = now().plusMinutes(jwtExpiration);
        final Map<String, Object> claims = claimMapper.map(user);

        final String jwt = Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(toDate(now()))
                .setExpiration(toDate(expiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .setClaims(claims)
                .compact();

        log.info("Generated JWT Token: {}", jwt);

        return jwt;
    }

    public Optional<UserPrincipal> getUser(final String jwt) {

        if (isBlank(jwt)) {
            log.info("JWT Token not provided");
            return empty();
        }

        log.info("Parsing JWT Token: {}", jwt);

        try {
            final Claims claims = parse(jwt).getBody();
            final UserPrincipal user = claimMapper.map(claims);

            log.info("Parsing OK! JWT Data: {}", user);
            return ofNullable(user);

        } catch (final Exception ex) {
            log.info("Error parsing JWT Token: {}", ex);
            return empty();
        }
    }

    private Jws<Claims> parse(final String jwt) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
    }
}
