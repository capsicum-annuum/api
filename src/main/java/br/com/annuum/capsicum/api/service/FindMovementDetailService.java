package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.MovementDetailsResponse;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.mapper.MovementDetailResponseMapper;
import br.com.annuum.capsicum.api.repository.MovementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindMovementDetailService {

    @Autowired
    private MovementRepository repository;

    @Autowired
    private MovementDetailResponseMapper mapper;

    public MovementDetailsResponse find(final Long id) {
        log.info("Searching for Movement with id '{}'", id);

        final Movement movement = repository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Movimento não encontrado!"));

        return mapper.map(movement);
    }

}
