package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.StatusUpdateNotAllowedException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import com.vividsolutions.jts.util.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.AccessControlException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UpdateCandidacyStatusServiceTest {

    @InjectMocks
    private UpdateCandidacyStatusService updateCandidacyStatusService;

    @Mock
    private FindCandidacyByIdService findCandidacyByIdService;

    @Mock
    private FindMovimentByNeedService findMovimentByNeedService;

    @Mock
    private CandidacyRepository candidacyRepository;

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "APPROVED", "DECLINED", "PRESENT", "ABSENT"})
    public void shouldReturnExceptionWhenUpdatingCandidacyStatusForMovementCancelled(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.CANCELLED);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(StatusUpdateNotAllowedException.class, () -> updateCandidacyStatusService.update(1L,1L, candidacyStatus));
    }


    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"ABSENT", "PRESENT"})
    public void shouldReturnExceptionWhenUpdatingCandidacyStatusNotAllowedForMovementActive(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(StatusUpdateNotAllowedException.class, () -> updateCandidacyStatusService.update(1L,1L, candidacyStatus));

    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"REJECTED", "APPROVED", "DECLINED"})
    public void shouldUpdateCandidacyStatusAllowedForMovementActiveWhenCurrentStatusIsCandidate(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.ACTIVE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L,1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "APPROVED"})
    public void shouldUpdateCandidacyStatusAllowedForMovementActiveWhenCurrentStatusIsRejected(CandidacyStatus candidacyStatus) {
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
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L,1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "DECLINED"})
    public void shouldUpdateCandidacyStatusAllowedForMovementActiveWhenCurrentStatusIsApproved(CandidacyStatus candidacyStatus) {
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
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L,1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "APPROVED"})
    public void shouldUpdateCandidacyStatusAllowedForMovementActiveWhenCurrentStatusIsDeclined(CandidacyStatus candidacyStatus) {
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
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L,1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }


    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "APPROVED", "DECLINED"})
    public void shouldReturnExceptionWhenUpdatingCandidacyStatusNotAllowedForMovementConclude(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.CONCLUDE);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(StatusUpdateNotAllowedException.class, () -> updateCandidacyStatusService.update(1L,1L, candidacyStatus));
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"PRESENT", "ABSENT"})
    public void shouldUpdateCandidacyStatusAllowedForMovementConclude(CandidacyStatus candidacyStatus) {
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
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.save(candidacy))
            .thenReturn(candidacy);

        final Candidacy returnedCandidacy = updateCandidacyStatusService.update(1L,1L, candidacyStatus);

        Assert.equals(returnedCandidacy.getCandidacyStatusControl().getStatusEnum(), candidacyStatus);
        Mockito.verify(candidacyRepository, times(1)).save(candidacy);
    }

    @ParameterizedTest
    @EnumSource(value = CandidacyStatus.class, names = {"CANDIDATE", "REJECTED", "APPROVED", "DECLINED", "PRESENT", "ABSENT"})
    public void shouldReturnExceptionWhenUserNotAllowedTriesToUpdateCandidacyStatus(CandidacyStatus candidacyStatus) {
        final Candidacy candidacy = new Candidacy()
            .setCandidacyStatusControl(new CandidacyStatusControl())
            .setNeed(Mockito.mock(Need.class))
            .setUserCandidate(Mockito.mock(UserVolunteer.class));

        final Movement movement = new Movement()
            .setUserAuthor(new UserGroup().setId(1L))
            .setMovementStatus(MovementStatus.CANCELLED);

        Mockito.when(findCandidacyByIdService.find(1L))
            .thenReturn(candidacy);
        Mockito.when(findMovimentByNeedService.find(candidacy.getNeed()))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> updateCandidacyStatusService.update(2L, 1L, candidacyStatus));
    }

}
