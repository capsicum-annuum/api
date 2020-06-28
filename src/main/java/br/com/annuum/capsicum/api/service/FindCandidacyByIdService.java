package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindCandidacyByIdService {

    @Autowired
    private CandidacyRepository candidacyRepository;

    public Candidacy find(Long id) {
        log.info("Searching for Candidacy with id: '{}'", id);
        return candidacyRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Candidatura não encontrada!"));
    }

}
