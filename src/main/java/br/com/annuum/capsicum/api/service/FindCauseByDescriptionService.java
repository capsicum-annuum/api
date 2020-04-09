package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.CauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindCauseByDescriptionService {

    @Autowired
    private CauseRepository causeRepository;

    public Cause find(String description) {
        return causeRepository.findByDescription(description).orElseThrow(RegisterNotFoundException::new);
    }
}
