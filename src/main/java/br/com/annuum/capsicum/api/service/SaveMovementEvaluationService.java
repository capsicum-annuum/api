package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementAuthorEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.MovementAuthorEvaluation;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.MovementAuthorEvaluationRepository;
import br.com.annuum.capsicum.api.validator.SaveEvaluationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveMovementEvaluationService {

    @Autowired
    private MovementAuthorEvaluationRepository movementAuthorEvaluationRepository;

    @Autowired
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Autowired
    private SaveEvaluationValidator saveEvaluationValidator;

    @Transactional
    public MovementAuthorEvaluation save(final Long idUserAuthenticated, final MovementAuthorEvaluationRequest movementAuthorEvaluationRequest) {

        log.info("Start to create an MovementAuthorEvaluation for: '{}'", movementAuthorEvaluationRequest);
        final Candidacy candidacy = findCandidacyByIdService.find(movementAuthorEvaluationRequest.getCandidacyId());

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        if (!candidacy.getUserCandidate().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException("O usuário autenticado não está autorizado a avaliar este Movimento.");
        }

        saveEvaluationValidator.validate(movement, candidacy);

        log.info("Building MovementAuthorEvaluation to persist");
        final MovementAuthorEvaluation movementAuthorEvaluation = new MovementAuthorEvaluation()
            .setCandidacy(candidacy)
            .setNote(movementAuthorEvaluationRequest.getNote())
            .setFeedback(movementAuthorEvaluationRequest.getFeedback());

        log.info("Creating a new MovementAuthorEvaluation: '{}'", movementAuthorEvaluation);
        return movementAuthorEvaluationRepository.save(movementAuthorEvaluation);
    }

}
