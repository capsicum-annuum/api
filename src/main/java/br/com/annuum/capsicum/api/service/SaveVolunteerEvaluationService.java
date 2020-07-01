package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.VolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.VolunteerEvaluation;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.VolunteerEvaluationRepository;
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
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Transactional
    public VolunteerEvaluation save(final Long idUserAuthenticated, final VolunteerEvaluationRequest volunteerEvaluationRequest) {

        log.info("Start to create an VolunteerEvaluation for: '{}'", volunteerEvaluationRequest);
        final Candidacy candidacy = findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy());

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        if (!movement.getUserAuthor().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException("O usuário autenticado não é o autor do movimento.");
        }

        if (!movement.getMovementStatus().equals(MovementStatus.CONCLUDE)) {
            throw new AccessControlException("Não é possível avaliar voluntários enquanto o Movimento não estiver concluído.");
        }

        if (!candidacy.getCandidacyStatusControl().getStatusEnum().equals(CandidacyStatus.PRESENT)) {
            throw new AccessControlException("Não é possível avaliar um voluntário com status da candidatura diferente de PRESENT.");
        }

        log.info("Building VolunteerEvaluation to persist");
        final VolunteerEvaluation volunteerEvaluation = new VolunteerEvaluation()
            .setCandidacy(candidacy)
            .setNote(volunteerEvaluationRequest.getNote())
            .setFeedback(volunteerEvaluationRequest.getFeedback());

        log.info("Creating a new VolunteerEvaluation: '{}'", volunteerEvaluation);
        return volunteerEvaluationRepository.save(volunteerEvaluation);
    }

}
