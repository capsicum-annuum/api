package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.CityRequest;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.repository.CityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindOrCreateNewCityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public City findOrCreateNewCity(CityRequest cityRequest) {
        Optional<City> cityOpt = cityRepository.findByGooglePlaceCityId(cityRequest.getGooglePlaceCityId());
        if (cityOpt.isPresent()) {
            return cityOpt.get();
        } else {
            City city = modelMapper.map(cityRequest, City.class);
            return cityRepository.save(city);
        }
    }
}
