package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.LocationCoordinates;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.repository.UserGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class SaveUserGroupService {

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private SaveAddressService saveAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public UserGroupResponse save(UserGroupRequest userGroupRequest) {

        final Address address = saveAddressService.saveAddress(userGroupRequest.getAddressRequest());
        final List<Cause> causesThatSupport = userGroupRequest.getCauseThatSupport().stream()
                .map(cause -> findCauseByDescriptionService.find(cause))
                .collect(Collectors.toList());

        final UserGroup userGroup = modelMapper.map(userGroupRequest, UserGroup.class)
                .setAddress(address)
                .setCauseThatSupport(causesThatSupport);

        userGroup.setCreatedAt(LocalDateTime.now());

        if (nonNull(userGroupRequest.getActualLocationCoordinatesRequest())) {
            userGroup.setActualLocationCoordinates(modelMapper.map(userGroupRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class));
        }

        return modelMapper.map(userGroupRepository.save(userGroup), UserGroupResponse.class);
    }
}
