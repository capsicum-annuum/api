package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveAddressService {

    @Autowired
    private FindOrCreateNewCityService findOrCreateNewCityService;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Address saveAddress(AddressRequest addressRequest) {
        final City city = findOrCreateNewCityService.findOrCreateNewCity(addressRequest.getCityRequest());
        final Address addressToPersist = modelMapper.map(addressRequest, Address.class)
                .setCity(city);
        return addressRepository.save(addressToPersist);
    }

}
