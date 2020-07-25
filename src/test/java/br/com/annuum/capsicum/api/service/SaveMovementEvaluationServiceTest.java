package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.MovementEvaluationRepository;
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
class SaveMovementEvaluationServiceTest {

    @InjectMocks
    private SaveMovementEvaluationService saveMovementEvaluationService;

    @Mock
    private MovementEvaluationRepository movementEvaluationRepository;

    @Mock
    private FindMovementByNeedService findMovementByNeedService;

    @Mock
    private FindCandidacyByIdService findCandidacyByIdService;

    @Mock
    private SaveEvaluationValidator saveEvaluationValidator;

    @Test
    public void mustSaveNewMovementEvaluation_withSuccess() {
        // Arrange
        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.PRESENT);

        final UserOrganization userEvaluated = Mockito.mock(UserOrganization.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final MovementEvaluation expectedMovementEvaluation = new MovementEvaluation()
            .setCandidacy(candidacy)
            .setRate(1)
            .setFeedback("someFeedBack");

        final MovementEvaluationRequest movementEvaluationRequest = new MovementEvaluationRequest()
            .setRate(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long userEvaluatorId = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(movementEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(movementEvaluationRepository.save(expectedMovementEvaluation))
            .thenReturn(expectedMovementEvaluation);
        Mockito.doNothing().when(saveEvaluationValidator).validate(movement, candidacy);

        //Act
        final MovementEvaluation returnedMovementEvaluation =
            saveMovementEvaluationService.save(userEvaluatorId, movementEvaluationRequest);

        //Assert
        assertEquals(expectedMovementEvaluation, returnedMovementEvaluation);
        Mockito.verify(movementEvaluationRepository, times(1)).save(expectedMovementEvaluation);
    }

    @Test
    public void shouldReturnExceptionWhenUserAuthenticatedIsNotUserCandidate() {
        // Arrange
        final UserVolunteer userEvaluator = new UserVolunteer();
        userEvaluator.setId(1L);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.PRESENT);

        final UserOrganization userEvaluated = Mockito.mock(UserOrganization.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final MovementEvaluationRequest movementEvaluationRequest = new MovementEvaluationRequest()
            .setRate(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long userEvaluatorId = 2L;

        Mockito.when(findCandidacyByIdService.find(movementEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveMovementEvaluationService.save(userEvaluatorId, movementEvaluationRequest));
    }

}
