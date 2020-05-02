package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.controller.request.UserOrganizationRequest;
import br.com.annuum.capsicum.api.controller.response.UserOrganizationResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.UserOrganization;
import br.com.annuum.capsicum.api.repository.UserOrganizationRepository;
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
public class SaveUserOrganizationService {

    @Autowired
    private FindCauseByDescriptionService findCauseByDescriptionService;

    @Autowired
    private UserOrganizationRepository userOrganizationRepository;

    @Autowired
    private SaveAddressService saveAddressService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PointFactory pointFactory;

    @Transactional
    public UserOrganizationResponse save(final UserOrganizationRequest userOrganizationRequest) {

        log.info("Start to create an UserOrganization for: '{}'", userOrganizationRequest);
        final Address address = saveAddressService.saveAddress(userOrganizationRequest.getAddressRequest());

        final List<Cause> causesThatSupport = userOrganizationRequest.getCauseThatSupport().stream()
            .map(cause -> findCauseByDescriptionService.find(cause))
            .collect(Collectors.toList());

        log.info("Building UserOrganization to persist");
        final UserOrganization userOrganization = modelMapper.map(userOrganizationRequest, UserOrganization.class)
            .setAddress(address)
            .setCauseThatSupport(causesThatSupport);

        userOrganization.setCreatedAt(LocalDateTime.now());

        log.info("Creating a new UserOrganization: '{}'", userOrganization);
        return modelMapper.map(userOrganizationRepository.save(userOrganization), UserOrganizationResponse.class);
    }
}
