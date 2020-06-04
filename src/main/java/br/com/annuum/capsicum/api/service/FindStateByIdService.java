package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindStateByIdService {

    @Autowired
    private StateRepository stateRepository;

    public City find(final Long idState) {
        log.info("Searching for State with id '{}'", idState);
        return stateRepository.findById(idState)
            .orElseThrow(() -> new RegisterNotFoundException("Estado não encontrado!"));
    }

}
