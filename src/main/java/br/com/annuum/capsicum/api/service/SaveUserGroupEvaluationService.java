package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserGroupEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.domain.UserGroupEvaluation;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
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
    private FindUserGroupByIdService findUserGroupByIdService;

    @Autowired
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Autowired
    private FindMovementByIdService findMovementByIdService;

    @Transactional
    public UserGroupEvaluation save(final UserGroupEvaluationRequest userGroupEvaluationRequest) {

        log.info("Start to create an UserGroupEvaluation for: '{}'", userGroupEvaluationRequest);
        final UserGroup userGroupEvaluated = findUserGroupByIdService.find(userGroupEvaluationRequest.getUserGroupEvaluatedId());

        final UserVolunteer userVolunteerEvaluator = findUserVolunteerByIdService.find(userGroupEvaluationRequest.getUserVolunteerEvaluatorId());

        final Movement movement = findMovementByIdService.find(userGroupEvaluationRequest.getMovementId());

        log.info("Building UserGroupEvaluation to persist");
        final UserGroupEvaluation userGroupEvaluation = new UserGroupEvaluation()
            .setUserGroupEvaluated(userGroupEvaluated)
            .setUserVolunteerEvaluator(userVolunteerEvaluator)
            .setMovement(movement)
            .setNote(userGroupEvaluationRequest.getNote())
            .setFeedback(userGroupEvaluationRequest.getFeedback());

        log.info("Creating a new UserGroupEvaluation: '{}'", userGroupEvaluation);
        return userGroupEvaluationRepository.save(userGroupEvaluation);
    }

}
