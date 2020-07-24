package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindMovementByNeedService {

    @Autowired
    private MovementRepository movementRepository;

    public Movement find(final Need need) {
        log.info("Searching for Need '{}'", need);
        return movementRepository.findByNeedsContains(need)
            .orElseThrow(() -> new RegisterNotFoundException("Movimento não encontrado!"));
    }

}
