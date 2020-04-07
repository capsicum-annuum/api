package br.com.annuum.capsicum.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.annuum.capsicum.api.domain.CausesDomain;
import br.com.annuum.capsicum.api.repository.CauseRepository;

@Service
public class CauseService {

    @Autowired
    private CauseRepository repository;

    public void save(CausesDomain cause) {

        if(!repository.existsByDescriptionIgnoringCase(cause.getDescription())) {
            repository.save(cause);
        }

    }

}
