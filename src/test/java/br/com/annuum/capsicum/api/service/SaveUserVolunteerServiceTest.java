package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.AvailabilityRequest;
import br.com.annuum.capsicum.api.controller.request.DayShiftAvailabilityRequest;
import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.DayShift;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveUserVolunteerServiceTest {

    @InjectMocks
    private SaveUserVolunteerService saveUserVolunteerService;

    @Mock
    private FindCauseByIdService findCauseByIdService;

    @Mock
    private FindSkillByIdService findSkillByIdService;

    @Mock
    private UserVolunteerRepository userVolunteerRepository;

    @Mock
    private SaveAddressService saveAddressService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustSaveAndReturnNewUserVolunteer_withSuccess() {
        // Arrange
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("someCause");
        final Skill skill = new Skill()
            .setId(1L)
            .setDescription("someSkill");

        final DayShiftAvailabilityRequest dayShiftAvailabilityRequest = new DayShiftAvailabilityRequest()
            .setDayShift(DayShift.MORNING)
            .setDayOfWeek(DayOfWeek.MONDAY);

        final DayShiftAvailability dayShiftAvailability = new DayShiftAvailability()
            .setDayShift(DayShift.MORNING)
            .setDayOfWeek(DayOfWeek.MONDAY);

        final AvailabilityRequest availabilityRequest = new AvailabilityRequest()
            .setDayShiftAvailabilities(Collections.singletonList(dayShiftAvailabilityRequest));

        final Availability availability = new Availability()
            .setDayShiftAvailabilities(Collections.singletonList(dayShiftAvailability));

        final List<Skill> skillList = Collections.singletonList(skill);
        final List<Cause> causeList = Collections.singletonList(cause);

        final UserVolunteer userVolunteer = new UserVolunteer()
            .setAddress(address)
            .setCauseThatSupport(causeList)
            .setUserSkills(skillList)
            .setAvailability(availability);

        final UserVolunteerRequest userVolunteerRequest = new UserVolunteerRequest()
            .setAddressRequest(Mockito.mock(AddressRequest.class))
            .setCauseThatSupport(Collections.singletonList(1L))
            .setUserSkills(Collections.singletonList(1L))
            .setAvailability(availabilityRequest);

        final UserVolunteerResponse expectedUserVolunteerResponse = new UserVolunteerResponse()
            .setName("someUserName")
            .setDescription("someDescription")
            .setEmail("someEmail");

        Mockito.when(findCauseByIdService.find(cause.getId()))
            .thenReturn(cause);
        Mockito.when(findSkillByIdService.find(skill.getId()))
            .thenReturn(skill);
        Mockito.when(saveAddressService.saveAddress(userVolunteerRequest.getAddressRequest()))
            .thenReturn(address);
        Mockito.when(modelMapper.map(userVolunteerRequest, UserVolunteer.class))
            .thenReturn(userVolunteer);
        Mockito.when(userVolunteerRepository.save(userVolunteer))
            .thenReturn(userVolunteer);
        Mockito.when(modelMapper.map(userVolunteer, UserVolunteerResponse.class))
            .thenReturn(expectedUserVolunteerResponse);
        Mockito.when(modelMapper.map(dayShiftAvailabilityRequest, DayShiftAvailability.class))
            .thenReturn(dayShiftAvailability);

        // Act
        final UserVolunteerResponse returnedUserVolunteerResponse = saveUserVolunteerService.save(userVolunteerRequest);

        // Assert
        assertEquals(expectedUserVolunteerResponse, returnedUserVolunteerResponse);
        Mockito.verify(userVolunteerRepository, times(1)).save(userVolunteer);
    }
}
