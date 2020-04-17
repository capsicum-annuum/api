package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.controller.request.LocationCoordinatesRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.repository.AddressRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveAddressService {

    @Autowired
    private FindOrCreateNewCityService findOrCreateNewCityService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GeometryFactory geometryFactory;

    @Transactional
    public Address saveAddress(final AddressRequest addressRequest) {

        final City city = findOrCreateNewCityService.findOrCreateNewCity(addressRequest.getCityRequest());

        final LocationCoordinatesRequest locationCoordinatesRequest = addressRequest.getLocationCoordinatesRequest();

        final Geometry geography = geometryFactory.createPoint(new Coordinate(locationCoordinatesRequest.getLatitude(),
                locationCoordinatesRequest.getLongitude()));

        final Address addressToPersist = modelMapper.map(addressRequest, Address.class)
                .setCity(city)
                .setGeography(geography);

        log.info("Creating a new Address: '{}'", addressToPersist);
        return addressRepository.save(addressToPersist);
    }
}
