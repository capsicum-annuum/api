package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.security.UserPrincipal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    public String greetings(final Optional<UserPrincipal> currentUser) {
        if (currentUser.isPresent()) {
            return "Hello, " + currentUser.get().getName() + " | " + currentUser.get().getProfile();
        }

        return "Hello, Guest | GUEST";
    }

}
