package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.City;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindCityByIdService {

    @Autowired
    private CityRepository cityRepository;

    public City find(final Long idCity) {
        log.info("Searching for City with id '{}'", idCity);
        return cityRepository.findById(idCity)
            .orElseThrow(() -> new RegisterNotFoundException("Cidade não encontrada!"));
    }

}
