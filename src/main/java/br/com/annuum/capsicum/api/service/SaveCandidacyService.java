package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.CandidacyStatusControl;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.exceptions.DuplicateElementException;
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

    @Autowired
    private FindMovementByNeedService findMovementByNeedService;

    @Transactional
    public Candidacy save(final Long idUserAuthenticated, final Long idNeed) {

        log.info("Start to create an Candidacy");
        final UserVolunteer userVolunteer = findUserVolunteerByIdService.find(idUserAuthenticated);

        final Need need = findNeedByIdService.find(idNeed);

        if (!findMovementByNeedService.find(need).getMovementStatus().equals(MovementStatus.ACTIVE)) {
            throw new AccessControlException("Não é possível candidatar-se a um movimento com status diferente de ativo.");
        }

        if (candidacyRepository.existsByNeedIdAndUserCandidateId(idUserAuthenticated, idNeed)) {
            throw new DuplicateElementException("Já existe uma candidatura do usuário para esta necessidade. ");
        }

        final Candidacy candidacyToPersist = new Candidacy()
            .setNeed(need)
            .setUserCandidate(userVolunteer)
            .setCandidacyStatusControl(new CandidacyStatusControl());

        log.info("Creating a new Candidacy: '{}'", candidacyToPersist);
        return candidacyRepository.save(candidacyToPersist);
    }

}
