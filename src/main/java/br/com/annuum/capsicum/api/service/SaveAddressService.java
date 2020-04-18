package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.repository.AddressRepository;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
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

        final Coordinate coordinate = new Coordinate(addressRequest.getLatitude(), addressRequest.getLongitude());

        final Point geolocation = geometryFactory.createPoint(coordinate);

        final Address addressToPersist = modelMapper.map(addressRequest, Address.class)
                .setCity(city)
                .setGeolocation(geolocation);

        log.info("Creating a new Address: '{}'", addressToPersist);
        return addressRepository.save(addressToPersist);
    }
}
