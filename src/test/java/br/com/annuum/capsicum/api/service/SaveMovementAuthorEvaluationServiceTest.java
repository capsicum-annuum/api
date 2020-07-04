package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.MovementAuthorEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.MovementAuthorEvaluationRepository;
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
class SaveMovementAuthorEvaluationServiceTest {

    @InjectMocks
    private SaveMovementEvaluationService saveMovementEvaluationService;

    @Mock
    private MovementAuthorEvaluationRepository movementAuthorEvaluationRepository;

    @Mock
    private FindMovimentByNeedService findMovimentByNeedService;

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

        final MovementAuthorEvaluation expectedMovementAuthorEvaluation = new MovementAuthorEvaluation()
            .setCandidacy(candidacy)
            .setNote(1)
            .setFeedback("someFeedBack");

        final MovementAuthorEvaluationRequest movementAuthorEvaluationRequest = new MovementAuthorEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(movementAuthorEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(movementAuthorEvaluationRepository.save(expectedMovementAuthorEvaluation))
            .thenReturn(expectedMovementAuthorEvaluation);
        Mockito.doNothing().when(saveEvaluationValidator).validate(movement, candidacy);

        //Act
        final MovementAuthorEvaluation returnedMovementAuthorEvaluation =
            saveMovementEvaluationService.save(userEvaluatorId, movementAuthorEvaluationRequest);

        //Assert
        assertEquals(expectedMovementAuthorEvaluation, returnedMovementAuthorEvaluation);
        Mockito.verify(movementAuthorEvaluationRepository, times(1)).save(expectedMovementAuthorEvaluation);
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

        final MovementAuthorEvaluationRequest movementAuthorEvaluationRequest = new MovementAuthorEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = 2L;

        Mockito.when(findCandidacyByIdService.find(movementAuthorEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveMovementEvaluationService.save(userEvaluatorId, movementAuthorEvaluationRequest));
    }

}
