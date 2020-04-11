package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.LocationCoordinatesRequest;
import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.LocationCoordinates;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.repository.UserGroupRepository;
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
class SaveUserGroupServiceTest {

    @InjectMocks
    private SaveUserGroupService saveUserGroupService;

    @Mock
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Mock
    private UserGroupRepository userGroupRepository;

    @Mock
    private SaveAddressService saveAddressService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustSaveAndReturnNewUserGroup_withSuccess() {
        // Arrange
        final LocationCoordinates locationCoordinates = new LocationCoordinates()
                .setLatitude(1D)
                .setLongitude(1D);
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
                .setId(1L)
                .setDescription("someCause");
        final UserGroup userGroup = new UserGroup()
                .setAddress(address)
                .setCauseThatSupport(Collections.singletonList(cause));
        userGroup.setCreatedAt(LocalDateTime.now());
        final UserGroupResponse expectedUserGroupResponse = new UserGroupResponse()
                .setName("someUserName")
                .setDescription("someDescription")
                .setEmail("someEmail");
        final UserGroupRequest userGroupRequest = new UserGroupRequest()
                .setAddressRequest(Mockito.mock(AddressRequest.class))
                .setCauseThatSupport(Collections.singletonList("someCause"))
                .setActualLocationCoordinatesRequest(Mockito.mock(LocationCoordinatesRequest.class));

        Mockito.when(findCauseByDescriptionService.find(cause.getDescription()))
                .thenReturn(cause);
        Mockito.when(saveAddressService.saveAddress(userGroupRequest.getAddressRequest()))
                .thenReturn(address);
        Mockito.when(modelMapper.map(userGroupRequest, UserGroup.class))
                .thenReturn(userGroup);
        Mockito.when(modelMapper.map(userGroupRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class))
                .thenReturn(locationCoordinates);
        Mockito.when(userGroupRepository.save(userGroup))
                .thenReturn(userGroup);
        Mockito.when(modelMapper.map(userGroup, UserGroupResponse.class))
                .thenReturn(expectedUserGroupResponse);

        // Act
        UserGroupResponse returnedUserGroupResponse = saveUserGroupService.save(userGroupRequest);

        // Assert
        assertEquals(expectedUserGroupResponse, returnedUserGroupResponse);
        Mockito.verify(userGroupRepository, times(1)).save(userGroup);
    }

}