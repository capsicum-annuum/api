package br.com.annuum.capsicum.api.security;

import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import static br.com.annuum.capsicum.api.domain.enums.Profile.VOLUNTEER;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserPrincipalTest {

  @Test
  public void shouldCreateAnserPrincipal() {

    final Long id = RandomUtils.nextLong();
    final String name = randomAlphanumeric(10);
    final String email = randomAlphanumeric(10);
    final String password = randomAlphanumeric(10);

    final User user = new UserVolunteer()
        .setId(id)
        .setName(name)
        .setEmail(email)
        .setPassword(password);

    final UserPrincipal userPrincipal = UserPrincipal.create(user);

    assertEquals(id, userPrincipal.getId());
    assertEquals(name, userPrincipal.getName());
    assertEquals(email, userPrincipal.getEmail());
    assertEquals(password, userPrincipal.getPassword());
    assertEquals(VOLUNTEER, userPrincipal.getProfile());
    assertEquals("[ROLE_USER, ROLE_VOLUNTEER]", userPrincipal.getAuthorities().toString());
    assertTrue(userPrincipal.getEnabled());
    assertTrue(userPrincipal.isAccountNonExpired());
    assertTrue(userPrincipal.isAccountNonLocked());
    assertTrue(userPrincipal.isCredentialsNonExpired());
    assertTrue(userPrincipal.isEnabled());

  }
}