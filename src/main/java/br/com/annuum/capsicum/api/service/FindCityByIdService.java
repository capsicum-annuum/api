package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindCityByIdService {

    @Autowired
    private CityRepository cityRepository;

    public City find(Long id) {
        return cityRepository.findById(id).orElseThrow(RegisterNotFoundException::new);
    }
}
