package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.*;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.domain.enums.DayShift;
import br.com.annuum.capsicum.api.exceptions.AlreadyInUseException;
import br.com.annuum.capsicum.api.mapper.AvailabilityMapper;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveUserVolunteerServiceTest {

    @InjectMocks
    private SaveUserVolunteerService saveUserVolunteerService;

    @Mock
    private  UniqueUserService uniqueUserService;

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

    @Mock
    private AvailabilityMapper availabilityMapper;

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

        when(findCauseByIdService.find(cause.getId()))
            .thenReturn(cause);
        when(findSkillByIdService.find(skill.getId()))
            .thenReturn(skill);
        when(saveAddressService.save(userVolunteerRequest.getAddressRequest()))
            .thenReturn(address);
        when(modelMapper.map(userVolunteerRequest, UserVolunteer.class))
            .thenReturn(userVolunteer);
        when(userVolunteerRepository.save(userVolunteer))
            .thenReturn(userVolunteer);
        when(modelMapper.map(userVolunteer, UserVolunteerResponse.class))
            .thenReturn(expectedUserVolunteerResponse);
        when(availabilityMapper.map(availabilityRequest))
            .thenReturn(availability);

        // Act
        final UserVolunteerResponse returnedUserVolunteerResponse = saveUserVolunteerService.save(userVolunteerRequest);

        // Assert
        assertEquals(expectedUserVolunteerResponse, returnedUserVolunteerResponse);
        Mockito.verify(userVolunteerRepository, times(1)).save(userVolunteer);
    }

    @Test
    public void mustThrowAlreadyInUseExceptionWhenUserVolunteerEmailAlreadyInUse() {
        final String email = randomAlphanumeric(10);
        final String phone = randomNumeric(11);

        final UserVolunteerRequest userVolunteerRequest = new UserVolunteerRequest()
            .setEmail(email)
            .setPhone(phone);

        final UniqueUserInformationRequest uniqueUser = new UniqueUserInformationRequest()
            .setEmail(email)
            .setPhone(phone);

        doThrow(AlreadyInUseException.class).when(uniqueUserService).validate(uniqueUser);

        assertThrows(AlreadyInUseException.class, () -> saveUserVolunteerService.save(userVolunteerRequest));

        verifyNoInteractions(findCauseByIdService);
        verifyNoInteractions(findSkillByIdService);
        verifyNoInteractions(saveAddressService);
        verifyNoInteractions(modelMapper);
        verifyNoInteractions(userVolunteerRepository);
        verifyNoInteractions(availabilityMapper);

    }
}
