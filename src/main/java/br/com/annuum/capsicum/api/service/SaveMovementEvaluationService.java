package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.MovementEvaluation;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.MovementEvaluationRepository;
import br.com.annuum.capsicum.api.validator.SaveEvaluationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveMovementEvaluationService {

    @Autowired
    private MovementEvaluationRepository movementEvaluationRepository;

    @Autowired
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Autowired
    private SaveEvaluationValidator saveEvaluationValidator;

    @Transactional
    public MovementEvaluation save(final Long idUserAuthenticated, final MovementEvaluationRequest movementEvaluationRequest) {

        log.info("Start to create an MovementEvaluation for: '{}'", movementEvaluationRequest);
        final Candidacy candidacy = findCandidacyByIdService.find(movementEvaluationRequest.getIdCandidacy());

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        if (!candidacy.getUserCandidate().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException("O usuário autenticado não está autorizado a avaliar este Movimento.");
        }

        saveEvaluationValidator.validate(movement, candidacy);

        log.info("Building MovementEvaluation to persist");
        final MovementEvaluation movementEvaluation = new MovementEvaluation()
            .setCandidacy(candidacy)
            .setRate(movementEvaluationRequest.getRate())
            .setFeedback(movementEvaluationRequest.getFeedback());

        log.info("Creating a new MovementEvaluation: '{}'", movementEvaluation);
        return movementEvaluationRepository.save(movementEvaluation);
    }

}
