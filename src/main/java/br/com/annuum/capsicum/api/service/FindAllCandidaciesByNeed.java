package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.CandidacyListResponse;
import br.com.annuum.capsicum.api.mapper.CandidacyMapper;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindAllCandidaciesByNeed {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private CandidacyMapper candidacyMapper;

    public CandidacyListResponse find(Long idNeed) {
        log.info("Searching for all Candidacies with Need id: '{}'", idNeed);
        return new CandidacyListResponse()
            .setCandidacies(candidacyMapper.map(candidacyRepository.findAllByNeedId(idNeed)));
    }

}
