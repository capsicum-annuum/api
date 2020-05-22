package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.AbstractUser;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindUserByIdService {

    @Autowired
    private UserRepository userRepository;

    public AbstractUser find(final Long id) {
        log.info("Searching for User with id '{}'", id);
        return (AbstractUser) userRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Usuário não encontrado!"));
    }
}
