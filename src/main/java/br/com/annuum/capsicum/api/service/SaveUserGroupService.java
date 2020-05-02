package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.UserGroup;
import br.com.annuum.capsicum.api.repository.UserGroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private PointFactory pointFactory;

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

        userGroup.setCreatedAt(LocalDateTime.now());

        log.info("Creating a new UserGroup: '{}'", userGroup);
        return modelMapper.map(userGroupRepository.save(userGroup), UserGroupResponse.class);
    }
}
