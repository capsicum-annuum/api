package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CauseService {

    @Autowired
    private CauseRepository repository;

    public void save(Cause cause) {

        if(!repository.existsByDescriptionIgnoringCase(cause.getDescription())) {
            repository.save(cause);
        }

    }

}
