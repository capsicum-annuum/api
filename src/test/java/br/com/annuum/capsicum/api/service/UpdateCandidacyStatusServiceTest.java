package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.exceptions.StatusUpdateNotAllowedException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import com.vividsolutions.jts.util.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UpdateCandidacyStatusServiceTest {

    @InjectMocks
    private UpdateCandidacyStatusService updateCandidacyStatusService;

    @Mock
    private FindCandidacyByIdService findCandidacyByIdService;

    @Mock
    private FindMovementByNeedService findMovementByNeedService;

    @Mock
    private CandidacyRepository candidacyRepository;

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "APPROVED", "DECLINED", "PRESENT", "ABSENT"})
    public void shouldReturnExceptionWhenUserAuthorUpdatesCandidacyStatusForMovementCancelled(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.CANCELLED);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(StatusUpdateNotAllowedException.class, () -> updateCandidacyStatusService.update(1L, 1L, candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "DECLINED"})
    public void shouldReturnExceptionWhenUserCandidateUpdatesCandidacyStatusForMovementCancelled(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate((UserVolunteer) new UserVolunteer().setId(1L));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(2L))
            .setMovementStatus(MovementStatus.CANCELLED);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(StatusUpdateNotAllowedException.class, () -> updateCandidacyStatusService.update(1L, 1L, candidacyStatus));
    }


    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"ABSENT", "PRESENT"})
    public void shouldReturnExceptionWhenUserAuthorUpdatesCandidacyStatusNotAllowedForMovementActive(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(StatusUpdateNotAllowedException.class, () -> updateCandidacyStatusService.update(1L, 1L, candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"REJECTED", "APPROVED"})
    public void shouldUpdateCandidacyStatusAllowedForMovementActiveWhenCurrentStatusIsCandidateAndTheUserAuthenticatedIsTheMovementAuthor(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L, 1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "APPROVED"})
    public void shouldUpdateCandidacyStatusAllowedForMovementActiveWhenCurrentStatusIsRejectedAndTheUserAuthenticatedIsTheMovementAuthor(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.REJECTED);

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L, 1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED"})
    public void shouldUpdateCandidacyStatusAllowedForMovementActiveWhenCurrentStatusIsApprovedAndTheUserAuthenticatedIsTheMovementAuthor(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L, 1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "APPROVED"})
    public void shouldReturnExceptionWhenUserAuthorUpdatesCandidacyStatusNotAllowedForMovementConclude(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.CONCLUDE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(StatusUpdateNotAllowedException.class, () -> updateCandidacyStatusService.update(1L, 1L, candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"PRESENT", "ABSENT"})
    public void shouldUpdateCandidacyStatusAllowedForMovementConcludeAndTheUserAuthenticatedIsTheMovementAuthor(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.APPROVED);

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.CONCLUDE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L, 1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @Test
    public void shouldUpdateCandidacyStatusToDeclinedForMovementActiveWhenCurrentStatusIsCandidateAndTheUserAuthenticatedIsTheCandidate() {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate((UserVolunteer) new UserVolunteer().setId(1L));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(2L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L, 1L, CandidacyStatus.DECLINED);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), CandidacyStatus.DECLINED);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @Test
    public void shouldUpdateCandidacyStatusToCandidateForMovementActiveWhenCurrentStatusIsDeclinedAndTheUserAuthenticatedIsTheCandidate() {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate((UserVolunteer) new UserVolunteer().setId(1L));
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.DECLINED);

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(2L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L, 1L, CandidacyStatus.CANDIDATE);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), CandidacyStatus.CANDIDATE);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @Test
    public void shouldReturnExceptionWhenUserAuthorUpdatesCandidacyStatusToDeclined() {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> updateCandidacyStatusService.update(1L, 1L, CandidacyStatus.DECLINED));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "APPROVED"})
    public void shouldReturnExceptionWhenCurrentCandidacyStatusIsDeclinedAndUserAuthorUpdatesIt(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));
        candidacy.getCandidacyStatusControl().setStatusEnum(CandidacyStatus.DECLINED);

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> updateCandidacyStatusService.update(1L, 1L, candidacyStatus));
    }


    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "APPROVED", "DECLINED", "PRESENT", "ABSENT"})
    public void shouldReturnExceptionWhenUserNotAllowedTriesToUpdateCandidacyStatus(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate((UserVolunteer) new UserVolunteer().setId(2L));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovementByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> updateCandidacyStatusService.update(3L, 1L, candidacyStatus));
    }

}
