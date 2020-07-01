package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.VolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.VolunteerEvaluationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
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
    private FindMovimentByNeedService findMovimentByNeedService;

    @Mock
    private FindCandidacyByIdService findCandidacyByIdService;

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
            .setNote(1)
            .setFeedback("someFeedBack");

        final VolunteerEvaluationRequest volunteerEvaluationRequest = new VolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(volunteerEvaluationRepository.save(expectedVolunteerEvaluation))
            .thenReturn(expectedVolunteerEvaluation);

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
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 2L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveVolunteerEvaluationService.save(idUserEvaluator, volunteerEvaluationRequest));
    }

    @Test
    public void mustReturnExceptionWhenUserEvaluatedHasCandidacyStatusCandidate() {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final VolunteerEvaluationRequest volunteerEvaluationRequest = new VolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveVolunteerEvaluationService.save(idUserEvaluator, volunteerEvaluationRequest));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"APPROVED", "DECLINED", "REJECTED"})
    public void mustReturnExceptionWhenUserEvaluatedHasCandidacyStatusApprovedOrDeclinedOrRejected(CandidacyStatus candidacyStatus) {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        candidacy.getCandidacyStatusControl().setStatusEnum(candidacyStatus);

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final VolunteerEvaluationRequest volunteerEvaluationRequest = new VolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveVolunteerEvaluationService.save(idUserEvaluator, volunteerEvaluationRequest));
    }


    @Test
    public void mustReturnExceptionWhenUserEvaluatedHasCandidacyStatusAbsent() {
        // Arrange
        Candidacy candidacy = new Candidacy()
            .setUserCandidate(Mockito.mock(UserVolunteer.class))
            .setCandidacyStatusControl(new CandidacyStatusControl());

        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.ABSENT);

        final UserGroup userEvaluator = new UserGroup();
        userEvaluator.setId(1L);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluator)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final VolunteerEvaluationRequest volunteerEvaluationRequest = new VolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveVolunteerEvaluationService.save(idUserEvaluator, volunteerEvaluationRequest));
    }

    @ParameterizedTest
    @EnumSource(value = MovementStatus.class, names = {"ACTIVE", "CANCELLED"})
    public void mustReturnExceptionWhenEvaluatingUserVolunteerMovementStatusDifferentThanConclude(MovementStatus movementStatus) {
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
            .setMovementStatus(movementStatus);

        final VolunteerEvaluationRequest volunteerEvaluationRequest = new VolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(volunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveVolunteerEvaluationService.save(idUserEvaluator, volunteerEvaluationRequest));
    }


}
