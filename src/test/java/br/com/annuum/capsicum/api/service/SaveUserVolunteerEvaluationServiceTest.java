package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.domain.UserVolunteerEvaluation;
import br.com.annuum.capsicum.api.repository.UserVolunteerEvaluationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveUserVolunteerEvaluationServiceTest {

    @InjectMocks
    private SaveUserVolunteerEvaluationService saveUserVolunteerEvaluationService;

    @Mock
    private UserVolunteerEvaluationRepository userVolunteerEvaluationRepository;

    @Mock
    private FindUserByIdService findUserByIdService;

    @Mock
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Mock
    private FindMovementByIdService findMovementByIdService;

    @Test
    public void mustSaveNewUserVolunteerEvaluation_withSuccess() {
        // Arrange
        final Movement movement = Mockito.mock(Movement.class);

        final UserVolunteer userVolunteerEvaluated = Mockito.mock(UserVolunteer.class);

        final UserGroup userEvaluator = Mockito.mock(UserGroup.class);

        final UserVolunteerEvaluation expectedUserVolunteerEvaluation = new UserVolunteerEvaluation()
            .setUserVolunteerEvaluated(userVolunteerEvaluated)
            .setUserEvaluator(userEvaluator)
            .setMovement(movement)
            .setNote(1)
            .setFeedback("someFeedBack");

        final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest = new UserVolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setMovementId(1L)
            .setUserEvaluatorId(1L)
            .setUserVolunteerEvaluatedId(1L);

        Mockito.when(findUserVolunteerByIdService.find(userVolunteerEvaluationRequest.getUserVolunteerEvaluatedId()))
            .thenReturn(userVolunteerEvaluated);
        Mockito.when(findUserByIdService.find(userVolunteerEvaluationRequest.getUserEvaluatorId()))
            .thenReturn(userEvaluator);
        Mockito.when(findMovementByIdService.find(userVolunteerEvaluationRequest.getMovementId()))
            .thenReturn(movement);
        Mockito.when(userVolunteerEvaluationRepository.save(expectedUserVolunteerEvaluation))
            .thenReturn(expectedUserVolunteerEvaluation);

        //Act
        final UserVolunteerEvaluation returnedUserVolunteerEvaluation =
            saveUserVolunteerEvaluationService.save(userVolunteerEvaluationRequest);

        //Assert
        assertEquals(expectedUserVolunteerEvaluation, returnedUserVolunteerEvaluation);
        Mockito.verify(userVolunteerEvaluationRepository, times(1)).save(expectedUserVolunteerEvaluation);
    }

}
