package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.UserVolunteerEvaluationRepository;
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
class SaveUserVolunteerEvaluationServiceTest {

    @InjectMocks
    private SaveUserVolunteerEvaluationService saveUserVolunteerEvaluationService;

    @Mock
    private UserVolunteerEvaluationRepository userVolunteerEvaluationRepository;

    @Mock
    private FindUserByIdService findUserByIdService;

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

        final UserVolunteerEvaluation expectedUserVolunteerEvaluation = new UserVolunteerEvaluation()
            .setUserVolunteerEvaluated(candidacy.getUserCandidate())
            .setUserEvaluator(userEvaluator)
            .setCandidacy(candidacy)
            .setNote(1)
            .setFeedback("someFeedBack");

        final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest = new UserVolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(userVolunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(findUserByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);
        Mockito.when(userVolunteerEvaluationRepository.save(expectedUserVolunteerEvaluation))
            .thenReturn(expectedUserVolunteerEvaluation);

        //Act
        final UserVolunteerEvaluation returnedUserVolunteerEvaluation =
            saveUserVolunteerEvaluationService.save(idUserEvaluator, userVolunteerEvaluationRequest);

        //Assert
        assertEquals(expectedUserVolunteerEvaluation, returnedUserVolunteerEvaluation);
        Mockito.verify(userVolunteerEvaluationRepository, times(1)).save(expectedUserVolunteerEvaluation);
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

        final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest = new UserVolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 2L;

        Mockito.when(findCandidacyByIdService.find(userVolunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(findUserByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);

        assertThrows(AccessControlException.class, () -> saveUserVolunteerEvaluationService.save(idUserEvaluator, userVolunteerEvaluationRequest));
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

        final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest = new UserVolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(userVolunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(findUserByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);

        assertThrows(AccessControlException.class, () -> saveUserVolunteerEvaluationService.save(idUserEvaluator, userVolunteerEvaluationRequest));
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

        final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest = new UserVolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(userVolunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(findUserByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);

        assertThrows(AccessControlException.class, () -> saveUserVolunteerEvaluationService.save(idUserEvaluator, userVolunteerEvaluationRequest));
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

        final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest = new UserVolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(userVolunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(findUserByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);

        assertThrows(AccessControlException.class, () -> saveUserVolunteerEvaluationService.save(idUserEvaluator, userVolunteerEvaluationRequest));
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

        final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest = new UserVolunteerEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setIdCandidacy(1L);

        final Long idUserEvaluator = 1L;

        Mockito.when(findCandidacyByIdService.find(userVolunteerEvaluationRequest.getIdCandidacy()))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(findUserByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);

        assertThrows(AccessControlException.class, () -> saveUserVolunteerEvaluationService.save(idUserEvaluator, userVolunteerEvaluationRequest));
    }


}
