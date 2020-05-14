package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserGroupEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.domain.UserGroupEvaluation;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.repository.UserGroupEvaluationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveUserGroupEvaluationServiceTest {

    @InjectMocks
    private SaveUserGroupEvaluationService saveUserGroupEvaluationService;

    @Mock
    private UserGroupEvaluationRepository userGroupEvaluationRepository;

    @Mock
    private FindUserGroupByIdService findUserGroupByIdService;

    @Mock
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Mock
    private FindMovementByIdService findMovementByIdService;

    @Test
    public void mustSaveNewUserGroupEvaluation_withSuccess() {
        // Arrange
        final Movement movement = Mockito.mock(Movement.class);

        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        final UserGroup userEvaluated = Mockito.mock(UserGroup.class);

        final UserGroupEvaluation expectedUserGroupEvaluation = new UserGroupEvaluation()
            .setUserGroupEvaluated(userEvaluated)
            .setUserVolunteerEvaluator(userEvaluator)
            .setMovement(movement)
            .setNote(1)
            .setFeedback("someFeedBack");

        final UserGroupEvaluationRequest userGroupEvaluationRequest = new UserGroupEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setMovementId(1L)
            .setUserGroupEvaluatedId(1L)
            .setUserVolunteerEvaluatorId(1L);

        Mockito.when(findUserVolunteerByIdService.find(userGroupEvaluationRequest.getUserVolunteerEvaluatorId()))
            .thenReturn(userEvaluator);
        Mockito.when(findUserGroupByIdService.find(userGroupEvaluationRequest.getUserGroupEvaluatedId()))
            .thenReturn(userEvaluated);
        Mockito.when(findMovementByIdService.find(userGroupEvaluationRequest.getMovementId()))
            .thenReturn(movement);
        Mockito.when(userGroupEvaluationRepository.save(expectedUserGroupEvaluation))
            .thenReturn(expectedUserGroupEvaluation);

        //Act
        final UserGroupEvaluation returnedUserGroupEvaluation =
            saveUserGroupEvaluationService.save(userGroupEvaluationRequest);

        //Assert
        assertEquals(expectedUserGroupEvaluation, returnedUserGroupEvaluation);
        Mockito.verify(userGroupEvaluationRepository, times(1)).save(expectedUserGroupEvaluation);
    }

}
