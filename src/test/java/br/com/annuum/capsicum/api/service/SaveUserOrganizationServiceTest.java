package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.LocationCoordinatesRequest;
import br.com.annuum.capsicum.api.controller.request.UserOrganizationRequest;
import br.com.annuum.capsicum.api.controller.response.UserOrganizationResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.LocationCoordinates;
import br.com.annuum.capsicum.api.domain.UserOrganization;
import br.com.annuum.capsicum.api.repository.UserOrganizationRepository;
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
class SaveUserOrganizationServiceTest {

    @InjectMocks
    private SaveUserOrganizationService saveUserOrganizationService;

    @Mock
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Mock
    private UserOrganizationRepository userOrganizationRepository;

    @Mock
    private SaveAddressService saveAddressService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustSaveAndReturnNewUserOrganization_withSuccess() {
        // Arrange
        final LocationCoordinates locationCoordinates = new LocationCoordinates()
                .setLatitude(1D)
                .setLongitude(1D);
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
                .setId(1L)
                .setDescription("someCause");
        final UserOrganization userOrganization = new UserOrganization()
                .setAddress(address)
                .setCauseThatSupport(Collections.singletonList(cause));
        userOrganization.setCreatedAt(LocalDateTime.now());
        final UserOrganizationResponse expectedUserOrganizationResponse = new UserOrganizationResponse()
                .setName("someUserName")
                .setDescription("someDescription")
                .setEmail("someEmail");
        final UserOrganizationRequest userOrganizationRequest = new UserOrganizationRequest()
                .setAddressRequest(Mockito.mock(AddressRequest.class))
                .setCauseThatSupport(Collections.singletonList("someCause"))
                .setActualLocationCoordinatesRequest(Mockito.mock(LocationCoordinatesRequest.class));

        Mockito.when(findCauseByDescriptionService.find(cause.getDescription()))
                .thenReturn(cause);
        Mockito.when(saveAddressService.saveAddress(userOrganizationRequest.getAddressRequest()))
                .thenReturn(address);
        Mockito.when(modelMapper.map(userOrganizationRequest, UserOrganization.class))
                .thenReturn(userOrganization);
        Mockito.when(modelMapper.map(userOrganizationRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class))
                .thenReturn(locationCoordinates);
        Mockito.when(userOrganizationRepository.save(userOrganization))
                .thenReturn(userOrganization);
        Mockito.when(modelMapper.map(userOrganization, UserOrganizationResponse.class))
                .thenReturn(expectedUserOrganizationResponse);

        // Act
        final UserOrganizationResponse returnedUserOrganizationResponse = saveUserOrganizationService.save(userOrganizationRequest);

        // Assert
        assertEquals(expectedUserOrganizationResponse, returnedUserOrganizationResponse);
        Mockito.verify(userOrganizationRepository, times(1)).save(userOrganization);
    }

}