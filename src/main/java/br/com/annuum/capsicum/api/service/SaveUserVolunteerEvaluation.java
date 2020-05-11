package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.AbstractUser;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.domain.UserVolunteerEvaluation;
import br.com.annuum.capsicum.api.repository.UserVolunteerEvaluationRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveUserVolunteerEvaluation {

    @Autowired
    private UserVolunteerEvaluationRepository userVolunteerEvaluationRepository;

    @Autowired
    private FindUserByIdService findUserByIdService;

    @Autowired
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Autowired
    private FindMovementByIdService findMovementByIdService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public void save(final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest) {

        log.info("Start to create an UserVolunteerEvaluation for: '{}'", userVolunteerEvaluationRequest);
        final UserVolunteer userVolunteerEvaluated = findUserVolunteerByIdService.find(userVolunteerEvaluationRequest.getUserVolunteerEvaluatedId());

        final AbstractUser userEvaluator = findUserByIdService.find(userVolunteerEvaluationRequest.getUserEvaluatorId());

        final Movement movement = findMovementByIdService.find(userVolunteerEvaluationRequest.getMovementId());

        log.info("Building UserVolunteerEvaluation to persist");
        final UserVolunteerEvaluation userVolunteerEvaluation = new UserVolunteerEvaluation()
            .setUserVolunteerEvaluated(userVolunteerEvaluated)
            .setUserEvaluator(userEvaluator)
            .setMovement(movement)
            .setNote(userVolunteerEvaluationRequest.getNote())
            .setFeedback(userVolunteerEvaluationRequest.getFeedback());

        log.info("Creating a new UserVolunteerEvaluation: '{}'", userVolunteerEvaluation);
        userVolunteerEvaluationRepository.save(userVolunteerEvaluation);
    }

}
