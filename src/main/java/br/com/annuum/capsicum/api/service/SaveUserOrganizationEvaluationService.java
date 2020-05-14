package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserOrganizationEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.UserOrganization;
import br.com.annuum.capsicum.api.domain.UserOrganizationEvaluation;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
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
    private FindUserOrganizationByIdService findUserOrganizationByIdService;

    @Autowired
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Autowired
    private FindMovementByIdService findMovementByIdService;

    @Transactional
    public UserOrganizationEvaluation save(final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest) {

        log.info("Start to create an UserOrganizationEvaluation for: '{}'", userOrganizationEvaluationRequest);
        final UserOrganization userOrganizationEvaluated = findUserOrganizationByIdService.find(userOrganizationEvaluationRequest.getUserOrganizationEvaluatedId());

        final UserVolunteer userVolunteerEvaluator = findUserVolunteerByIdService.find(userOrganizationEvaluationRequest.getUserVolunteerEvaluatorId());

        final Movement movement = findMovementByIdService.find(userOrganizationEvaluationRequest.getMovementId());

        log.info("Building UserOrganizationEvaluation to persist");
        final UserOrganizationEvaluation userOrganizationEvaluation = new UserOrganizationEvaluation()
            .setUserOrganizationEvaluated(userOrganizationEvaluated)
            .setUserVolunteerEvaluator(userVolunteerEvaluator)
            .setMovement(movement)
            .setNote(userOrganizationEvaluationRequest.getNote())
            .setFeedback(userOrganizationEvaluationRequest.getFeedback());

        log.info("Creating a new UserOrganizationEvaluation: '{}'", userOrganizationEvaluation);
        return userOrganizationEvaluationRepository.save(userOrganizationEvaluation);
    }

}
