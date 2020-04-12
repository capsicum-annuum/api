package br.com.annuum.capsicum.api.security;


import static br.com.annuum.capsicum.api.test.utils.ArrayUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JwtClaimMapperTest {

  @InjectMocks
  private JwtClaimMapper target;

  @Test
  public void shouldMapAnUserToClaim() {

    final Long id = RandomUtils.nextLong();
    final String name = randomAlphanumeric(10);
    final String email = randomAlphanumeric(10);
    final String password = randomAlphanumeric(10);
    final Profile profile = random(Profile.values());
    final String roles = profile.toRoles();

    final User user = new UserPrincipal()
        .setId(id)
        .setName(name)
        .setEmail(email)
        .setPassword(password)
        .setProfile(profile);

    final Map<String, Object> map = target.map(user);

    assertEquals(id, Long.valueOf(map.get("id").toString()));
    assertEquals(name, map.get("name"));
    assertEquals(email, map.get("email"));
    assertEquals(profile, map.get("profile"));
    assertEquals(roles, map.get("roles"));

    // the password should not be serialized
    assertNull(map.get("password"));
    assertEquals(5, map.size());
  }


  @Test
  public void shouldMapClaimsToUser() {

    final Long id = RandomUtils.nextLong();
    final String name = randomAlphanumeric(10);
    final String email = randomAlphanumeric(10);
    final String password = randomAlphanumeric(10);
    final Profile profile = random(Profile.values());
    final String roles = profile.toRoles();

    final Map<String, Object> claims = new HashMap<>();

    claims.put("id", id);
    claims.put("name", name);
    claims.put("email", email);
    claims.put("password", password);
    claims.put("profile", profile);
    claims.put("roles", roles);

    final UserPrincipal user = target.map(claims);

    assertEquals(id, user.getId());
    assertEquals(name, user.getName());
    assertEquals(email, user.getEmail());
    assertEquals(profile, user.getProfile());
    assertEquals(roles, String.join(", ", user.getProfile().getRoles()));

    // the password should not be serialized
    assertNull(user.getPassword());
  }
}