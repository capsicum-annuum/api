package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserOrganizationEvaluationRequest;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.UserOrganization;
import br.com.annuum.capsicum.api.domain.UserOrganizationEvaluation;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.repository.UserOrganizationEvaluationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveUserOrganizationEvaluationServiceTest {

    @InjectMocks
    private SaveUserOrganizationEvaluationService saveUserOrganizationEvaluationService;

    @Mock
    private UserOrganizationEvaluationRepository userOrganizationEvaluationRepository;

    @Mock
    private FindUserOrganizationByIdService findUserOrganizationByIdService;

    @Mock
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Mock
    private FindMovementByIdService findMovementByIdService;

    @Test
    public void mustSaveNewUserOrganizationEvaluation_withSuccess() {
        // Arrange
        final Movement movement = Mockito.mock(Movement.class);

        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        final UserOrganization userEvaluated = Mockito.mock(UserOrganization.class);

        final UserOrganizationEvaluation expectedUserOrganizationEvaluation = new UserOrganizationEvaluation()
            .setUserOrganizationEvaluated(userEvaluated)
            .setUserVolunteerEvaluator(userEvaluator)
            .setMovement(movement)
            .setNote(1)
            .setFeedback("someFeedBack");

        final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest = new UserOrganizationEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setMovementId(1L)
            .setUserOrganizationEvaluatedId(1L)
            .setUserVolunteerEvaluatorId(1L);

        Mockito.when(findUserVolunteerByIdService.find(userOrganizationEvaluationRequest.getUserVolunteerEvaluatorId()))
            .thenReturn(userEvaluator);
        Mockito.when(findUserOrganizationByIdService.find(userOrganizationEvaluationRequest.getUserOrganizationEvaluatedId()))
            .thenReturn(userEvaluated);
        Mockito.when(findMovementByIdService.find(userOrganizationEvaluationRequest.getMovementId()))
            .thenReturn(movement);
        Mockito.when(userOrganizationEvaluationRepository.save(expectedUserOrganizationEvaluation))
            .thenReturn(expectedUserOrganizationEvaluation);

        //Act
        final UserOrganizationEvaluation returnedUserOrganizationEvaluation =
            saveUserOrganizationEvaluationService.save(userOrganizationEvaluationRequest);

        //Assert
        assertEquals(expectedUserOrganizationEvaluation, returnedUserOrganizationEvaluation);
        Mockito.verify(userOrganizationEvaluationRepository, times(1)).save(expectedUserOrganizationEvaluation);
    }

}
