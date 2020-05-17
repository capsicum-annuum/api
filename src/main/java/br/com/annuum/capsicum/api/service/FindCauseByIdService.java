package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindCauseByIdService {

    @Autowired
    private CauseRepository causeRepository;

    public Cause find(final Long id) {
        log.info("Searching for Cause with id '{}'", id);
        return causeRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Causa não encontrada!"));
    }

}
