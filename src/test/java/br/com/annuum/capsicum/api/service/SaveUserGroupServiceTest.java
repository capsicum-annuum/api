package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.ActualLocationRequest;
import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.domain.ActualLocation;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.repository.UserGroupRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
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

    @Mock
    private GeometryFactory geometryFactory;

    @Test
    public void mustSaveAndReturnNewUserGroup_withSuccess() {
        // Arrange
        final Point geolocation = Mockito.mock(Point.class);
        final ActualLocation actualLocation = new ActualLocation()
                .setActualGeolocation(geolocation)
                .setActualLatitude(1D)
                .setActualLongitude(1D);
        final Coordinate coordinate = new Coordinate(actualLocation.getActualLatitude(), actualLocation.getActualLongitude());
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
                .setId(1L)
                .setDescription("someCause");
        final UserGroup userGroup = new UserGroup()
                .setActualLocation(actualLocation)
                .setAddress(address)
                .setCauseThatSupport(Collections.singletonList(cause));
        userGroup.setCreatedAt(LocalDateTime.now());
        final UserGroupRequest userGroupRequest = new UserGroupRequest()
                .setActualLocationRequest(Mockito.mock(ActualLocationRequest.class))
                .setAddressRequest(Mockito.mock(AddressRequest.class))
                .setCauseThatSupport(Collections.singletonList("someCause"));
        final UserGroupResponse expectedUserGroupResponse = new UserGroupResponse()
                .setName("someUserName")
                .setDescription("someDescription")
                .setEmail("someEmail");

        Mockito.when(findCauseByDescriptionService.find(cause.getDescription()))
                .thenReturn(cause);
        Mockito.when(saveAddressService.saveAddress(userGroupRequest.getAddressRequest()))
                .thenReturn(address);
        Mockito.when(modelMapper.map(userGroupRequest, UserGroup.class))
                .thenReturn(userGroup);
        Mockito.when(modelMapper.map(userGroupRequest.getActualLocationRequest(), ActualLocation.class))
                .thenReturn(actualLocation);
        Mockito.when(geometryFactory.createPoint(coordinate))
                .thenReturn(geolocation);
        Mockito.when(userGroupRepository.save(userGroup))
                .thenReturn(userGroup);
        Mockito.when(modelMapper.map(userGroup, UserGroupResponse.class))
                .thenReturn(expectedUserGroupResponse);

        // Act
        final UserGroupResponse returnedUserGroupResponse = saveUserGroupService.save(userGroupRequest);

        // Assert
        assertEquals(expectedUserGroupResponse, returnedUserGroupResponse);
        Mockito.verify(userGroupRepository, times(1)).save(userGroup);
    }

}