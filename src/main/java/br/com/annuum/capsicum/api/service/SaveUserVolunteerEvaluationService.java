package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.AbstractUser;
import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.UserVolunteerEvaluation;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.UserVolunteerEvaluationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveUserVolunteerEvaluationService {

    @Autowired
    private UserVolunteerEvaluationRepository userVolunteerEvaluationRepository;

    @Autowired
    private FindUserByIdService findUserByIdService;

    @Autowired
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Transactional
    public UserVolunteerEvaluation save(final Long idUserAuthenticated, final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest) {

        log.info("Start to create an UserVolunteerEvaluation for: '{}'", userVolunteerEvaluationRequest);
        final Candidacy candidacy = findCandidacyByIdService.find(userVolunteerEvaluationRequest.getIdCandidacy());

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        final AbstractUser userEvaluator = findUserByIdService.find(idUserAuthenticated);

        if (!movement.getUserAuthor().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException("O usuário autenticado não é o autor do movimento.");
        }

        if (!movement.getMovementStatus().equals(MovementStatus.CONCLUDE)) {
            throw new AccessControlException("Não é possível avaliar voluntários enquanto o Movimento não estiver concluído.");
        }

        if (!candidacy.getCandidacyStatusControl().getStatusEnum().equals(CandidacyStatus.PRESENT)) {
            throw new AccessControlException("Não é possível avaliar um voluntário com status da candidatura diferente de PRESENT.");
        }

        log.info("Building UserVolunteerEvaluation to persist");
        final UserVolunteerEvaluation userVolunteerEvaluation = new UserVolunteerEvaluation()
            .setUserVolunteerEvaluated(candidacy.getUserCandidate())
            .setUserEvaluator(userEvaluator)
            .setCandidacy(candidacy)
            .setNote(userVolunteerEvaluationRequest.getNote())
            .setFeedback(userVolunteerEvaluationRequest.getFeedback());

        log.info("Creating a new UserVolunteerEvaluation: '{}'", userVolunteerEvaluation);
        return userVolunteerEvaluationRepository.save(userVolunteerEvaluation);
    }

}
