package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.AbstractUser;
import br.com.annuum.capsicum.api.domain.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepository extends Repository<AbstractUser, Long> {

    Optional<User> findById(final Long id);

    Optional<User> findByEmail(final String username);

}
