package br.com.annuum.capsicum.api.security;

import br.com.annuum.capsicum.api.domain.User;
import br.com.annuum.capsicum.api.exceptions.InvalidUserException;
import br.com.annuum.capsicum.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final User user = repository.findByEmail(username)
            .orElseThrow(InvalidUserException::new);
        return UserPrincipal.create(user);
    }

}
