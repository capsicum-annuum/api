package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.VolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.VolunteerEvaluation;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.exceptions.DuplicateElementException;
import br.com.annuum.capsicum.api.repository.VolunteerEvaluationRepository;
import br.com.annuum.capsicum.api.validator.SaveEvaluationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveVolunteerEvaluationService {

    @Autowired
    private VolunteerEvaluationRepository volunteerEvaluationRepository;

    @Autowired
    private FindMovementByNeedService findMovementByNeedService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Autowired
    private SaveEvaluationValidator saveEvaluationValidator;

    @Transactional
    public VolunteerEvaluation save(final Long idUserAuthenticated, final VolunteerEvaluationRequest volunteerEvaluationRequest) {

        log.info("Start to create an VolunteerEvaluation for: '{}'", volunteerEvaluationRequest);
        final Candidacy candidacy = findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy());

        final Movement movement = findMovementByNeedService.find(candidacy.getNeed());

        if (!movement.getUserAuthor().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException("O usuário autenticado não é o autor do movimento.");
        }

        if (volunteerEvaluationRepository.existsByCandidacyId(candidacy.getId())) {
            throw new DuplicateElementException("Já existe uma avaliação do usuário para este voluntário. ");
        }

        saveEvaluationValidator.validate(movement, candidacy);

        log.info("Building VolunteerEvaluation to persist");
        final VolunteerEvaluation volunteerEvaluation = new VolunteerEvaluation()
            .setCandidacy(candidacy)
            .setRate(volunteerEvaluationRequest.getRate())
            .setFeedback(volunteerEvaluationRequest.getFeedback());

        log.info("Creating a new VolunteerEvaluation: '{}'", volunteerEvaluation);
        return volunteerEvaluationRepository.save(volunteerEvaluation);
    }

}
