package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.CityResponse;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GetCityLocationByIdService {

    @Autowired
    private FindCityByIdService findCityByIdService;

    @Autowired
    private ModelMapper modelMapper;

    public CityResponse find(final Long idCity) {
        log.info("Starting to retrieve City with id '{}' as CityResponse", idCity);
        return modelMapper.map(findCityByIdService.find(idCity), CityResponse.class);
    }

}
