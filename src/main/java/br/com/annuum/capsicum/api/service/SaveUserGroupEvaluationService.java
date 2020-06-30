package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserGroupEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.UserGroupEvaluationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveUserGroupEvaluationService {

    @Autowired
    private UserGroupEvaluationRepository userGroupEvaluationRepository;

    @Autowired
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Autowired
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Transactional
    public UserGroupEvaluation save(final Long idUserAuthenticated, final UserGroupEvaluationRequest userGroupEvaluationRequest) {

        log.info("Start to create an UserGroupEvaluation for: '{}'", userGroupEvaluationRequest);
        final Candidacy candidacy = findCandidacyByIdService.find(userGroupEvaluationRequest.getCandidacyId());

        final UserVolunteer userVolunteerEvaluator = findUserVolunteerByIdService.find(idUserAuthenticated);

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        if (!candidacy.getUserCandidate().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException("O usuário autenticado não está autorizado a avaliar este Grupo voluntário.");
        }

        if (!candidacy.getCandidacyStatusControl().getStatusEnum().equals(CandidacyStatus.PRESENT)) {
            throw new AccessControlException("Somente voluntários presentes no Movimento podem avaliar este Grupo voluntário.");
        }

        if (!movement.getMovementStatus().equals(MovementStatus.CONCLUDE)) {
            throw new AccessControlException("Não é possível avaliar este Grupo voluntário enquanto o Movimento não estiver concluído.");
        }

        log.info("Building UserGroupEvaluation to persist");
        final UserGroupEvaluation userGroupEvaluation = new UserGroupEvaluation()
            .setUserGroupEvaluated((UserGroup) movement.getUserAuthor())
            .setUserVolunteerEvaluator(userVolunteerEvaluator)
            .setCandidacy(candidacy)
            .setNote(userGroupEvaluationRequest.getNote())
            .setFeedback(userGroupEvaluationRequest.getFeedback());

        log.info("Creating a new UserGroupEvaluation: '{}'", userGroupEvaluation);
        return userGroupEvaluationRepository.save(userGroupEvaluation);
    }

}
