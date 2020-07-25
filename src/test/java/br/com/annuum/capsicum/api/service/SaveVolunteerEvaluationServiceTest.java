package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.VolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.VolunteerEvaluationRepository;
import br.com.annuum.capsicum.api.validator.SaveEvaluationValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveVolunteerEvaluationServiceTest {

    @InjectMocks
    private SaveVolunteerEvaluationService saveVolunteerEvaluationService;

    @Mock
    private VolunteerEvaluationRepository volunteerEvaluationRepository;

    @Mock
    private FindMovementByNeedService findMovementByNeedService;

    @Mock
    private FindCandidacyByIdService findCandidacyByIdService;

    @Mock
    private SaveEvaluationValidator saveEvaluationValidator;

    @Test
    public void mustSaveNewUserVolunteerEvaluation_withSuccess() {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.PRESENT);

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final VolunteerEvaluation expectedVolunteerEvaluation = new VolunteerEvaluation()
            .setCandidacy(candidacy)
            .setRate(1)
            .setFeedback("someFeedBack");

        final VolunteerEvaluationRequest volunteerEvaluationRequest = new VolunteerEvaluationRequest()
            .setRate(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(volunteerEvaluationRepository.save(expectedVolunteerEvaluation))
            .thenReturn(expectedVolunteerEvaluation);
        Mockito.doNothing().when(saveEvaluationValidator).validate(movement, candidacy);

        //Act
        final VolunteerEvaluation returnedVolunteerEvaluation =
            saveVolunteerEvaluationService.save(idUserEvaluator, volunteerEvaluationRequest);

        //Assert
        assertEquals(expectedVolunteerEvaluation, returnedVolunteerEvaluation);
        Mockito.verify(volunteerEvaluationRepository, times(1)).save(expectedVolunteerEvaluation);
    }

    @Test
    public void mustReturnExceptionWhenUserAuthenticatedIsNotTheMovementAuthor() {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.PRESENT);

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final VolunteerEvaluationRequest volunteerEvaluationRequest = new VolunteerEvaluationRequest()
            .setRate(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 2L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveVolunteerEvaluationService.save(idUserEvaluator, volunteerEvaluationRequest));
    }

}
