package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.LocationCoordinatesRequest;
import br.com.annuum.capsicum.api.controller.request.UserOrganizationRequest;
import br.com.annuum.capsicum.api.controller.response.UserOrganizationResponse;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.LocationCoordinates;
import br.com.annuum.capsicum.api.domain.UserOrganization;
import br.com.annuum.capsicum.api.repository.UserOrganizationRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

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
    private GeometryFactory geometryFactory;

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

        if (nonNull(userOrganizationRequest.getActualLocationCoordinatesRequest())) {
            log.info("Getting LocationCoordinates from user");
            final LocationCoordinatesRequest actualLocationCoordinatesRequest = userOrganizationRequest.getActualLocationCoordinatesRequest();

            final Geometry geography = geometryFactory.createPoint(new Coordinate(actualLocationCoordinatesRequest.getLatitude(),
                    actualLocationCoordinatesRequest.getLongitude()));

            userOrganization.setActualLocationCoordinates(modelMapper.map(actualLocationCoordinatesRequest, LocationCoordinates.class));
            userOrganization.setGeography(geography);
        }

        userOrganization.setCreatedAt(LocalDateTime.now());

        log.info("Creating a new UserOrganization: '{}'", userOrganization);
        return modelMapper.map(userOrganizationRepository.save(userOrganization), UserOrganizationResponse.class);
    }
}
