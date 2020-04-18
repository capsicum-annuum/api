package br.com.annuum.capsicum.api.security;

import static br.com.annuum.capsicum.api.test.utils.ArrayUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.exceptions.InvalidUserException;
import br.com.annuum.capsicum.api.repository.UserRepository;
import java.util.Optional;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

  @InjectMocks
  private CustomUserDetailsService service;

  @Mock
  private UserRepository repository;

  @Test
  public void shouldReturnUserPrincipalSearchingForUsername() {

    final Long id = RandomUtils.nextLong();
    final String name = randomAlphanumeric(10);
    final String email = randomAlphanumeric(10);
    final String password = randomAlphanumeric(10);
    final Profile profile = random(Profile.values());

    final User user = new UserPrincipal()
        .setId(id)
        .setName(name)
        .setEmail(email)
        .setPassword(password)
        .setProfile(profile);

    when(repository.findByEmail(email))
        .thenReturn(Optional.of(user));

    final UserDetails userDetails = service.loadUserByUsername(email);
    final UserPrincipal userPrincipal = (UserPrincipal) userDetails;

    assertEquals(id, userPrincipal.getId());
    assertEquals(name, userPrincipal.getName());
    assertEquals(email, userPrincipal.getEmail());
    assertEquals(password, userPrincipal.getPassword());
    assertEquals(profile, userPrincipal.getProfile());
    assertTrue(userPrincipal.getEnabled());
  }

  @Test
  public void shouldThrowInvalidUserExceptionWhenUsernameNotValid() {

    final String email = randomAlphanumeric(10);

    when(repository.findByEmail(email))
        .thenReturn(Optional.empty());

    assertThrows(InvalidUserException.class, () -> service.loadUserByUsername(email));
  }

}