package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserOrganizationEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.UserOrganizationEvaluationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveUserOrganizationEvaluationService {

    @Autowired
    private UserOrganizationEvaluationRepository userOrganizationEvaluationRepository;

    @Autowired
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Autowired
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Transactional
    public UserOrganizationEvaluation save(final Long idUserAuthenticated, final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest) {

        log.info("Start to create an UserOrganizationEvaluation for: '{}'", userOrganizationEvaluationRequest);
        final Candidacy candidacy = findCandidacyByIdService.find(userOrganizationEvaluationRequest.getCandidacyId());

        final UserVolunteer userVolunteerEvaluator = findUserVolunteerByIdService.find(idUserAuthenticated);

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        if (!candidacy.getUserCandidate().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException("O usuário autenticado não está autorizado a avaliar este Movimento.");
        }

        if (!candidacy.getCandidacyStatusControl().getStatusEnum().equals(CandidacyStatus.PRESENT)) {
            throw new AccessControlException("Somente voluntários presentes no Movimento podem avaliar a Organização.");
        }

        if (!movement.getMovementStatus().equals(MovementStatus.CONCLUDE)) {
            throw new AccessControlException("Não é possível avaliar a organização enquanto o Movimento não estiver concluído.");
        }

        log.info("Building UserOrganizationEvaluation to persist");
        final UserOrganizationEvaluation userOrganizationEvaluation = new UserOrganizationEvaluation()
            .setUserOrganizationEvaluated((UserOrganization) movement.getUserAuthor())
            .setUserVolunteerEvaluator(userVolunteerEvaluator)
            .setCandidacy(candidacy)
            .setNote(userOrganizationEvaluationRequest.getNote())
            .setFeedback(userOrganizationEvaluationRequest.getFeedback());

        log.info("Creating a new UserOrganizationEvaluation: '{}'", userOrganizationEvaluation);
        return userOrganizationEvaluationRepository.save(userOrganizationEvaluation);
    }

}
