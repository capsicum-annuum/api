package br.com.annuum.capsicum.api.service;

import static java.util.Objects.nonNull;

import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.LocationCoordinates;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.repository.UserGroupRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
    public UserGroupResponse save(final UserGroupRequest userGroupRequest) {

        log.info("Start to create an UserGroup for: '{}'", userGroupRequest);
        final Address address = saveAddressService.saveAddress(userGroupRequest.getAddressRequest());

        final List<Cause> causesThatSupport = userGroupRequest.getCauseThatSupport().stream()
                .map(cause -> findCauseByDescriptionService.find(cause))
                .collect(Collectors.toList());

        log.info("Building UserGroup to persist");
        final UserGroup userGroup = modelMapper.map(userGroupRequest, UserGroup.class)
                .setAddress(address)
                .setCauseThatSupport(causesThatSupport);

        if (nonNull(userGroupRequest.getActualLocationCoordinatesRequest())) {
            log.info("Getting LocationCoordinates from user");
            userGroup.setActualLocationCoordinates(modelMapper.map(userGroupRequest.getActualLocationCoordinatesRequest(), LocationCoordinates.class));
        }

        log.info("Creating a new UserGroup: '{}'", userGroup);
        return modelMapper.map(userGroupRepository.save(userGroup), UserGroupResponse.class);
    }
}
