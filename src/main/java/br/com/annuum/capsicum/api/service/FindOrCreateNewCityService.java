package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.request.CityRequest;
import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class FindOrCreateNewCityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public City findOrCreateNewCity(final CityRequest cityRequest) {
        final String placeId = cityRequest.getGooglePlaceCityId();
        log.info("Searching for City with placeId '{}'", placeId);

        final Optional<City> cityOpt = cityRepository.findByGooglePlaceCityId(placeId);

        if (cityOpt.isPresent()) {
            return cityOpt.get();
        } else {
            log.info("Creating a new City: '{}'", cityRequest);
            final City city = modelMapper.map(cityRequest, City.class);
            return cityRepository.save(city);
        }
    }
}
