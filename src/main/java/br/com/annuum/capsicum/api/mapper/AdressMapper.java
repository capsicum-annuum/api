package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.request.AddressRequest;
import br.com.annuum.capsicum.api.domain.Address;
import br.com.annuum.capsicum.api.service.FindCityByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdressMapper {

    @Autowired
    private FindCityByIdService findCityByIdService;

    public Address map(AddressRequest addressRequest) {

        Address address = new Address();

        address.setCity(findCityByIdService.find(addressRequest.getIdCity()));
        address.setDistrict(addressRequest.getDistrict());
        address.setStreetName(addressRequest.getStreetName());
        address.setAddressNumber(addressRequest.getAddressNumber());
        address.setComplement(addressRequest.getComplement());

        return address;
    }
}
