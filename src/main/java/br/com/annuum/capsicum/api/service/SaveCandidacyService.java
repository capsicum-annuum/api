package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.CandidacyRequest;
import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.CandidacyStatusControl;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveCandidacyService {

    @Autowired
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Autowired
    private FindNeedByIdService findNeedByIdService;

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Transactional
    public Candidacy save(final CandidacyRequest candidacyRequest) {

        log.info("Start to create an Candidacy for: '{}'", candidacyRequest);
        final UserVolunteer userVolunteer = findUserVolunteerByIdService.find(candidacyRequest.getUserVolunteerId());

        final Need need = findNeedByIdService.find(candidacyRequest.getNeedId());

        final Candidacy candidacyToPersist = new Candidacy()
            .setNeed(need)
            .setUserCandidate(userVolunteer)
            .setCandidacyStatusControl(new CandidacyStatusControl());

        log.info("Creating a new Candidacy: '{}'", candidacyToPersist);
        return candidacyRepository.save(candidacyToPersist);
    }

}
