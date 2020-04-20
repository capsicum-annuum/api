package br.com.annuum.capsicum.api.service;

import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {

  public String greetings(final Optional<UserPrincipal> currentUser) {
    if (currentUser.isPresent()) {
      return "Hello, " + currentUser.get().getName() + " | " + currentUser.get().getProfile();
    }

    return "Hello, Guest | GUEST";
  }

}
