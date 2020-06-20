package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.CandidacyRequest;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.DayShift;
import br.com.annuum.capsicum.api.domain.enums.NeedStatus;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        final CandidacyRequest candidacyRequest = new CandidacyRequest()
            .setNeedId(1L)
            .setUserVolunteerId(1L);

        final Candidacy expectedCandidacy = new Candidacy()
            .setNeed(need)
            .setUserCandidate(userVolunteer)
            .setCandidacyStatusControl(new CandidacyStatusControl());

        Mockito.when(findUserVolunteerByIdService.find(candidacyRequest.getUserVolunteerId()))
            .thenReturn(userVolunteer);
        Mockito.when(findNeedByIdService.find(candidacyRequest.getNeedId()))
            .thenReturn(need);
        Mockito.when(candidacyRepository.save(expectedCandidacy))
            .thenReturn(expectedCandidacy);

        final Candidacy returnedCandidacy = saveCandidacyService.save(candidacyRequest);

        assertEquals(expectedCandidacy, returnedCandidacy);
        Mockito.verify(candidacyRepository, times(1)).save(expectedCandidacy);
    }

}
