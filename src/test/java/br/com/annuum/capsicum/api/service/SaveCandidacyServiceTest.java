package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.DayShift;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.domain.enums.NeedStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.exceptions.DuplicateElementException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import br.com.annuum.capsicum.api.validator.VolunteerEvaluationDebitsValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveCandidacyServiceTest {

    @InjectMocks
    private SaveCandidacyService saveCandidacyService;

    @Mock
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Mock
    private FindNeedByIdService findNeedByIdService;

    @Mock
    private CandidacyRepository candidacyRepository;

    @Mock
    private FindMovementByNeedService findMovementByNeedService;

    @Mock
    private VolunteerEvaluationDebitsValidator volunteerEvaluationDebitsValidator;

    @Test
    public void mustSaveAndReturnNewCandidacy_withSuccess() {
        // Arrange
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("someCause");
        final Skill skill = new Skill()
            .setId(1L)
            .setDescription("someSkill");

        final DayShiftAvailability dayShiftAvailability = new DayShiftAvailability()
            .setDayShift(DayShift.MORNING)
            .setDayOfWeek(DayOfWeek.MONDAY);

        final Availability availability = new Availability()
            .setDayShiftAvailabilities(Collections.singletonList(dayShiftAvailability));

        final List<Skill> skillList = Collections.singletonList(skill);
        final List<Cause> causeList = Collections.singletonList(cause);

        final UserVolunteer userVolunteer = new UserVolunteer()
            .setAddress(address)
            .setCauseThatSupport(causeList)
            .setUserSkills(skillList)
            .setAvailability(availability);

        final Need need = new Need()
            .setId(1L)
            .setSkill(skill)
            .setDescription("someDescription")
            .setQuantity(1)
            .setAvailability(availability)
            .setNeedStatus(NeedStatus.ACTIVE);

        final Candidacy expectedCandidacy = new Candidacy()
            .setNeed(need)
            .setUserCandidate(userVolunteer)
            .setCandidacyStatusControl(new CandidacyStatusControl());

        final Movement movement = new Movement()
            .setMovementStatus(MovementStatus.ACTIVE);

        final Long idUser = 1L;
        final Long idNeed = 1L;

        Mockito.when(findUserVolunteerByIdService.find(idUser))
            .thenReturn(userVolunteer);
        Mockito.doNothing().when(volunteerEvaluationDebitsValidator).validate(idUser);
        Mockito.when(findNeedByIdService.find(idNeed))
            .thenReturn(need);
        Mockito.when(findMovementByNeedService.find(need))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.existsByNeedIdAndUserCandidateId(idUser, idNeed))
            .thenReturn(Boolean.FALSE);
        Mockito.when(candidacyRepository.save(expectedCandidacy))
            .thenReturn(expectedCandidacy);

        final Candidacy returnedCandidacy = saveCandidacyService.save(idUser, idNeed);

        assertEquals(expectedCandidacy, returnedCandidacy);
        Mockito.verify(candidacyRepository, times(1)).save(expectedCandidacy);
    }

    @ParameterizedTest
    @EnumSource(value = MovementStatus.class, names = {"CONCLUDE", "CANCELLED"})
    public void shouldReturnExceptionWhenCandidacyForMovementNotActive(MovementStatus movementStatus) {
        // Arrange
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("someCause");
        final Skill skill = new Skill()
            .setId(1L)
            .setDescription("someSkill");

        final DayShiftAvailability dayShiftAvailability = new DayShiftAvailability()
            .setDayShift(DayShift.MORNING)
            .setDayOfWeek(DayOfWeek.MONDAY);

        final Availability availability = new Availability()
            .setDayShiftAvailabilities(Collections.singletonList(dayShiftAvailability));

        final List<Skill> skillList = Collections.singletonList(skill);
        final List<Cause> causeList = Collections.singletonList(cause);

        final UserVolunteer userVolunteer = new UserVolunteer()
            .setAddress(address)
            .setCauseThatSupport(causeList)
            .setUserSkills(skillList)
            .setAvailability(availability);

        final Need need = new Need()
            .setId(1L)
            .setSkill(skill)
            .setDescription("someDescription")
            .setQuantity(1)
            .setAvailability(availability)
            .setNeedStatus(NeedStatus.ACTIVE);

        final Movement movement = new Movement()
            .setMovementStatus(movementStatus);

        final Long idUser = 1L;
        final Long idNeed = 1L;

        Mockito.when(findUserVolunteerByIdService.find(idUser))
            .thenReturn(userVolunteer);
        Mockito.doNothing().when(volunteerEvaluationDebitsValidator).validate(idUser);
        Mockito.when(findNeedByIdService.find(idNeed))
            .thenReturn(need);
        Mockito.when(findMovementByNeedService.find(need))
            .thenReturn(movement);

        assertThrows(AccessControlException.class, () -> saveCandidacyService.save(idUser, idNeed));
    }

    @Test
    public void shouldReturnExceptionWhenCandidacyDuplicated() {
        // Arrange
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("someCause");
        final Skill skill = new Skill()
            .setId(1L)
            .setDescription("someSkill");

        final DayShiftAvailability dayShiftAvailability = new DayShiftAvailability()
            .setDayShift(DayShift.MORNING)
            .setDayOfWeek(DayOfWeek.MONDAY);

        final Availability availability = new Availability()
            .setDayShiftAvailabilities(Collections.singletonList(dayShiftAvailability));

        final List<Skill> skillList = Collections.singletonList(skill);
        final List<Cause> causeList = Collections.singletonList(cause);

        final UserVolunteer userVolunteer = new UserVolunteer()
            .setAddress(address)
            .setCauseThatSupport(causeList)
            .setUserSkills(skillList)
            .setAvailability(availability);

        final Need need = new Need()
            .setId(1L)
            .setSkill(skill)
            .setDescription("someDescription")
            .setQuantity(1)
            .setAvailability(availability)
            .setNeedStatus(NeedStatus.ACTIVE);

        final Movement movement = new Movement()
            .setMovementStatus(MovementStatus.ACTIVE);

        final Long idUser = 1L;
        final Long idNeed = 1L;

        Mockito.when(findUserVolunteerByIdService.find(idUser))
            .thenReturn(userVolunteer);
        Mockito.doNothing().when(volunteerEvaluationDebitsValidator).validate(idUser);
        Mockito.when(findNeedByIdService.find(idNeed))
            .thenReturn(need);
        Mockito.when(findMovementByNeedService.find(need))
            .thenReturn(movement);
        Mockito.when(candidacyRepository.existsByNeedIdAndUserCandidateId(idUser, idNeed))
            .thenReturn(Boolean.TRUE);

        assertThrows(DuplicateElementException.class, () -> saveCandidacyService.save(idUser, idNeed));
    }


}
