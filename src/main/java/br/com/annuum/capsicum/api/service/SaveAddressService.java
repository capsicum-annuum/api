package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class SaveAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PointFactory pointFactory;

    @Transactional
    public Address save(final AddressRequest addressRequest) {
        log.info("Start to create an Address for: '{}'", addressRequest);

        log.info("Building Address to persist");
        final Address addressToPersist = modelMapper.map(addressRequest, Address.class)
            .setGeolocation(pointFactory.createPointFromCoordinates(addressRequest.getLatitude(), addressRequest.getLongitude()));

        log.info("Creating a new Address: '{}'", addressToPersist);
        return addressRepository.save(addressToPersist);
    }

}
