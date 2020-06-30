package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserGroupEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.UserGroupEvaluationRepository;
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
class SaveUserGroupEvaluationServiceTest {

    @InjectMocks
    private SaveUserGroupEvaluationService saveUserGroupEvaluationService;

    @Mock
    private UserGroupEvaluationRepository userGroupEvaluationRepository;

    @Mock
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Mock
    private FindMovimentByNeedService findMovimentByNeedService;

    @Mock
    private FindCandidacyByIdService findCandidacyByIdService;

    @Test
    public void mustSaveNewUserGroupEvaluation_withSuccess() {
        // Arrange
        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.PRESENT);

        final UserGroup userEvaluated = Mockito.mock(UserGroup.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserGroupEvaluation expectedUserGroupEvaluation = new UserGroupEvaluation()
            .setUserGroupEvaluated(userEvaluated)
            .setUserVolunteerEvaluator(userEvaluator)
            .setCandidacy(candidacy)
            .setNote(1)
            .setFeedback("someFeedBack");

        final UserGroupEvaluationRequest userGroupEvaluationRequest = new UserGroupEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long idUserEvaluator = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(userGroupEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(userGroupEvaluationRepository.save(expectedUserGroupEvaluation))
            .thenReturn(expectedUserGroupEvaluation);

        //Act
        final UserGroupEvaluation returnedUserGroupEvaluation =
            saveUserGroupEvaluationService.save(idUserEvaluator, userGroupEvaluationRequest);

        //Assert
        assertEquals(expectedUserGroupEvaluation, returnedUserGroupEvaluation);
        Mockito.verify(userGroupEvaluationRepository, times(1)).save(expectedUserGroupEvaluation);
    }

    @Test
    public void shouldReturnExceptionWhenUserAuthenticatedIsNotUserCandidate() {
        // Arrange
        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.PRESENT);

        final UserGroup userEvaluated = Mockito.mock(UserGroup.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserGroupEvaluationRequest userGroupEvaluationRequest = new UserGroupEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long idUserEvaluator = 2L;

        Mockito.when(findCandidacyByIdService.find(userGroupEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserGroupEvaluationService.save(idUserEvaluator, userGroupEvaluationRequest));
    }

    @Test
    public void shouldReturnExceptionWhenUserEvaluatorHasStatusCandidate() {
        // Arrange
        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());

        final UserGroup userEvaluated = Mockito.mock(UserGroup.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserGroupEvaluationRequest userGroupEvaluationRequest = new UserGroupEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long idUserEvaluator = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(userGroupEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserGroupEvaluationService.save(idUserEvaluator, userGroupEvaluationRequest));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"APPROVED", "DECLINED", "REJECTED"})
    public void shouldReturnExceptionWhenUserEvaluatorHasStatusApprovedOrDeclinedOrRejected(CandidacyStatus candidacyStatus) {
        // Arrange
        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(candidacyStatus);

        final UserGroup userEvaluated = Mockito.mock(UserGroup.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserGroupEvaluationRequest userGroupEvaluationRequest = new UserGroupEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long idUserEvaluator = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(userGroupEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserGroupEvaluationService.save(idUserEvaluator, userGroupEvaluationRequest));
    }

    @Test
    public void shouldReturnExceptionWhenUserEvaluatorHasStatusAbsent() {
        // Arrange
        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.ABSENT);

        final UserGroup userEvaluated = Mockito.mock(UserGroup.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserGroupEvaluationRequest userGroupEvaluationRequest = new UserGroupEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long idUserEvaluator = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(userGroupEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserGroupEvaluationService.save(idUserEvaluator, userGroupEvaluationRequest));
    }

    @ParameterizedTest
    @EnumSource(value = MovementStatus.class, names = {"ACTIVE", "CANCELLED"})
    public void mustReturnExceptionWhenEvaluatingUserVolunteerMovementStatusDifferentThanConclude(MovementStatus movementStatus){
        // Arrange
        final UserVolunteer userEvaluator = Mockito.mock(UserVolunteer.class);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());

        final UserGroup userEvaluated = Mockito.mock(UserGroup.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(movementStatus);

        final UserGroupEvaluationRequest userGroupEvaluationRequest = new UserGroupEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long idUserEvaluator = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(userGroupEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(idUserEvaluator))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserGroupEvaluationService.save(idUserEvaluator, userGroupEvaluationRequest));
    }


}
