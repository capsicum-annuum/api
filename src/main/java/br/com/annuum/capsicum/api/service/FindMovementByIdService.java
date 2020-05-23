package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindMovementByIdService {

    @Autowired
    private MovementRepository movementRepository;

    public Movement find(final Long id) {
        log.info("Searching for Movement with id '{}'", id);
        return movementRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Movimento não encontrado!"));
    }
}
