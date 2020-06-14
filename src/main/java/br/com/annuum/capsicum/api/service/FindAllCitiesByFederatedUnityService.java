package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.CityListResponse;
import br.com.annuum.capsicum.api.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindAllCitiesByFederatedUnityService {

    @Autowired
    private CityRepository cityRepository;

    public CityListResponse find(Long idFederatedUnity) {
        log.info("Searching for all Cities with Federated Unity id '{}'", idFederatedUnity);
        return new CityListResponse()
            .setCities(cityRepository.findAllCitiesAsCityDtoByFederatedUnity(idFederatedUnity));
    }

}
