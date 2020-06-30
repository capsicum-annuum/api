package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserOrganizationEvaluationRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.repository.UserOrganizationEvaluationRepository;
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
class SaveUserOrganizationEvaluationServiceTest {

    @InjectMocks
    private SaveUserOrganizationEvaluationService saveUserOrganizationEvaluationService;

    @Mock
    private UserOrganizationEvaluationRepository userOrganizationEvaluationRepository;

    @Mock
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Mock
    private FindMovimentByNeedService findMovimentByNeedService;

    @Mock
    private FindCandidacyByIdService findCandidacyByIdService;

    @Test
    public void mustSaveNewUserOrganizationEvaluation_withSuccess() {
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

        final UserOrganizationEvaluation expectedUserOrganizationEvaluation = new UserOrganizationEvaluation()
            .setUserOrganizationEvaluated(userEvaluated)
            .setUserVolunteerEvaluator(userEvaluator)
            .setCandidacy(candidacy)
            .setNote(1)
            .setFeedback("someFeedBack");

        final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest = new UserOrganizationEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = userEvaluator.getId();

        Mockito.when(findCandidacyByIdService.find(userOrganizationEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(userEvaluatorId))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(userOrganizationEvaluationRepository.save(expectedUserOrganizationEvaluation))
            .thenReturn(expectedUserOrganizationEvaluation);

        //Act
        final UserOrganizationEvaluation returnedUserOrganizationEvaluation =
            saveUserOrganizationEvaluationService.save(userEvaluatorId, userOrganizationEvaluationRequest);

        //Assert
        assertEquals(expectedUserOrganizationEvaluation, returnedUserOrganizationEvaluation);
        Mockito.verify(userOrganizationEvaluationRepository, times(1)).save(expectedUserOrganizationEvaluation);
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

        final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest = new UserOrganizationEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = 2L;

        Mockito.when(findCandidacyByIdService.find(userOrganizationEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(userEvaluatorId))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserOrganizationEvaluationService.save(userEvaluatorId, userOrganizationEvaluationRequest));
    }

    @Test
    public void shouldReturnExceptionWhenUserEvaluatorHasStatusCandidate() {
        // Arrange
        final UserVolunteer userEvaluator = new UserVolunteer();
        userEvaluator.setId(1L);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());

        final UserOrganization userEvaluated = Mockito.mock(UserOrganization.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest = new UserOrganizationEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = 2L;

        Mockito.when(findCandidacyByIdService.find(userOrganizationEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(userEvaluatorId))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserOrganizationEvaluationService.save(userEvaluatorId, userOrganizationEvaluationRequest));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"APPROVED", "DECLINED", "REJECTED"})
    public void shouldReturnExceptionWhenUserEvaluatorHasStatusApprovedOrDeclinedOrRejected(CandidacyStatus candidacyStatus) {
        // Arrange
        final UserVolunteer userEvaluator = new UserVolunteer();
        userEvaluator.setId(1L);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(candidacyStatus);

        final UserOrganization userEvaluated = Mockito.mock(UserOrganization.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest = new UserOrganizationEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = 2L;

        Mockito.when(findCandidacyByIdService.find(userOrganizationEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(userEvaluatorId))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserOrganizationEvaluationService.save(userEvaluatorId, userOrganizationEvaluationRequest));
    }

    @Test
    public void shouldReturnExceptionWhenUserEvaluatorHasStatusAbsent() {
        // Arrange
        final UserVolunteer userEvaluator = new UserVolunteer();
        userEvaluator.setId(1L);

        Candidacy candidacy = new Candidacy()
            .setUserCandidate(userEvaluator)
            .setCandidacyStatusControl(new CandidacyStatusControl());
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.ABSENT);

        final UserOrganization userEvaluated = Mockito.mock(UserOrganization.class);

        final Movement movement = new Movement()
            .setUserAuthor(userEvaluated)
            .setMovementStatus(MovementStatus.CONCLUDE);

        final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest = new UserOrganizationEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = 2L;

        Mockito.when(findCandidacyByIdService.find(userOrganizationEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(userEvaluatorId))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserOrganizationEvaluationService.save(userEvaluatorId, userOrganizationEvaluationRequest));
    }

    @ParameterizedTest
    @EnumSource(value = MovementStatus.class, names = {"ACTIVE", "CANCELLED"})
    public void mustReturnExceptionWhenEvaluatingUserVolunteerMovementStatusDifferentThanConclude(MovementStatus movementStatus) {
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
            .setMovementStatus(movementStatus);

        final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest = new UserOrganizationEvaluationRequest()
            .setNote(1)
            .setFeedback("someFeedBack")
            .setCandidacyId(1L);

        final Long userEvaluatorId = 2L;

        Mockito.when(findCandidacyByIdService.find(userOrganizationEvaluationRequest.getCandidacyId()))
            .thenReturn(candidacy);
        Mockito.when(findUserVolunteerByIdService.find(userEvaluatorId))
            .thenReturn(userEvaluator);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveUserOrganizationEvaluationService.save(userEvaluatorId, userOrganizationEvaluationRequest));
    }

}
