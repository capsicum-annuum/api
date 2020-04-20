package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.LocationCoordinatesRequest;
import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.*;
import br.com.annuum.capsicum.api.repository.UserVolunteerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveUserVolunteerServiceTest {

    @InjectMocks
    private SaveUserVolunteerService saveUserVolunteerService;

    @Mock
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Mock
    private FindSkillByDescriptionService findSkillByDescriptionService;

    @Mock
    private UserVolunteerRepository userVolunteerRepository;

    @Mock
    private SaveAddressService saveAddressService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustSaveAndReturnNewUserVolunteer_withSuccess() {
        // Arrange
        final LocationCoordinates locationCoordinates = new LocationCoordinates()
                .setLatitude(1D)
                .setLongitude(1D);
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
                .setId(1L)
                .setDescription("someCause");
        final Skill skill = new Skill()
                .setId(1L)
                .setDescription("someSkill");
        final UserVolunteer userVolunteer = new UserVolunteer()
                .setActualLocationCoordinates(locationCoordinates)
                .setAddress(address)
                .setCauseThatSupport(Collections.singletonList(cause))
                .setUserSkills(Collections.singletonList(skill));
        final UserVolunteerRequest userVolunteerRequest = new UserVolunteerRequest()
                .setAddressRequest(Mockito.mock(AddressRequest.class))
                .setCauseThatSupport(Collections.singletonList("someCause"))
                .setUserSkills(Collections.singletonList("someSkill"))
                .setActualLocationCoordinatesRequest(Mockito.mock(LocationCoordinatesRequest.class));
        final UserVolunteerResponse expectedUserVolunteerResponse = new UserVolunteerResponse()
                .setName("someUserName")
                .setDescription("someDescription")
                .setEmail("someEmail");

        Mockito.when(findCauseByDescriptionService.find(cause.getDescription()))
                .thenReturn(cause);
        Mockito.when(findSkillByDescriptionService.find(skill.getDescription()))
                .thenReturn(skill);
        Mockito.when(saveAddressService.saveAddress(userVolunteerRequest.getAddressRequest()))
                .thenReturn(address);
        Mockito.when(modelMapper.map(userVolunteerRequest, UserVolunteer.class))
                .thenReturn(userVolunteer);
        Mockito.when(modelMapper.map(userVolunteerRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class))
                .thenReturn(locationCoordinates);
        Mockito.when(userVolunteerRepository.save(userVolunteer))
                .thenReturn(userVolunteer);
        Mockito.when(modelMapper.map(userVolunteer, UserVolunteerResponse.class))
                .thenReturn(expectedUserVolunteerResponse);

        // Act
        final UserVolunteerResponse returnedUserVolunteerResponse = saveUserVolunteerService.save(userVolunteerRequest);

        // Assert
        assertEquals(expectedUserVolunteerResponse, returnedUserVolunteerResponse);
        Mockito.verify(userVolunteerRepository, times(1)).save(userVolunteer);
    }
}