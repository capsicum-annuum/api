package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.response.CityListResponse;
import br.com.annuum.capsicum.api.controller.response.FederatedUnitiesListResponse;
import br.com.annuum.capsicum.api.domain.dto.FederatedUnityDto;
import br.com.annuum.capsicum.api.service.FindAllCitiesByFederatedUnityService;
import br.com.annuum.capsicum.api.service.FindAllFederatedUnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locality")
public class LocalityController {

    @Autowired
    private FindAllFederatedUnityService findAllFederatedUnityService;

    @Autowired
    private FindAllCitiesByFederatedUnityService findAllCitiesByFederatedUnityService;

    @GetMapping("/federated-unity")
    @ResponseStatus(HttpStatus.OK)
    public FederatedUnitiesListResponse findAllFederatedUnities() {
        return findAllFederatedUnityService.find();
    }

    @GetMapping("/city")
    @ResponseStatus(HttpStatus.OK)
    public CityListResponse findAllCitiesByFederatedUnities(@RequestParam("id") Long idFederatedUnity) {
        return findAllCitiesByFederatedUnityService.find(idFederatedUnity);
    }

}
