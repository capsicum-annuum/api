package br.com.annuum.capsicum.api.security;

import static java.lang.String.valueOf;

import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.mapper.Mapper;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JwtClaimMapper implements Mapper<User, Map<String, Object>> {

  @Override
  public Map<String, Object> map(final User user) {
    final Map<String, Object> map = new HashMap<>();

    map.put("id", user.getId());
    map.put("name", user.getName());
    map.put("email", user.getEmail());
    map.put("profile", user.getProfile());
    map.put("roles", user.getProfile().toRoles());

    return map;
  }

  public UserPrincipal map(final Map<String, Object> claims) {
    return new UserPrincipal()
        .setId(Long.parseLong(valueOf(claims.get("id"))))
        .setName(valueOf(claims.get("name")))
        .setEmail(valueOf(claims.get("email")))
        .setProfile(Profile.valueOf(valueOf(claims.get("profile"))));
  }

}
