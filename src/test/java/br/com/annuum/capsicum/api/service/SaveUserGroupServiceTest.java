package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.repository.UserGroupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SaveUserGroupServiceTest {

    @InjectMocks
    private SaveUserGroupService saveUserGroupService;

    @Mock
    private FindCauseByIdService findCauseByIdService;

    @Mock
    private UserGroupRepository userGroupRepository;

    @Mock
    private SaveAddressService saveAddressService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void mustSaveAndReturnNewUserGroup_withSuccess() {
        // Arrange
        final Address address = Mockito.mock(Address.class);
        final Cause cause = new Cause()
            .setId(1L)
            .setDescription("someCause");
        final UserGroup userGroup = new UserGroup()
            .setAddress(address)
            .setCauseThatSupport(Collections.singletonList(cause));
        final UserGroupRequest userGroupRequest = new UserGroupRequest()
            .setAddressRequest(Mockito.mock(AddressRequest.class))
            .setCauseThatSupport(Collections.singletonList(1L));
        final UserGroupResponse expectedUserGroupResponse = new UserGroupResponse()
            .setName("someUserName")
            .setDescription("someDescription")
            .setEmail("someEmail");

        Mockito.when(findCauseByIdService.find(cause.getId()))
            .thenReturn(cause);
        Mockito.when(saveAddressService.save(userGroupRequest.getAddressRequest()))
            .thenReturn(address);
        Mockito.when(modelMapper.map(userGroupRequest, UserGroup.class))
            .thenReturn(userGroup);
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
