package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.response.CityListResponse;
import br.com.annuum.capsicum.api.controller.response.CityResponse;
import br.com.annuum.capsicum.api.controller.response.FederatedUnitiesListResponse;
import br.com.annuum.capsicum.api.service.FindAllCitiesByFederatedUnityService;
import br.com.annuum.capsicum.api.service.FindAllFederatedUnitiesService;
import br.com.annuum.capsicum.api.service.GetCityLocationByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locality")
public class LocalityController {

    @Autowired
    private FindAllFederatedUnitiesService findAllFederatedUnitiesService;

    @Autowired
    private FindAllCitiesByFederatedUnityService findAllCitiesByFederatedUnityService;

    @Autowired
    private GetCityLocationByIdService getCityLocationByIdService;

    @GetMapping("/federated-unity")
    @ResponseStatus(HttpStatus.OK)
    public FederatedUnitiesListResponse findAllFederatedUnities() {
        return findAllFederatedUnitiesService.find();
    }

    @GetMapping("/city")
    @ResponseStatus(HttpStatus.OK)
    public CityListResponse findAllCitiesByFederatedUnities(@RequestParam("id") Long idFederatedUnity) {
        return findAllCitiesByFederatedUnityService.find(idFederatedUnity);
    }

    @GetMapping("/city/{idCity}")
    @ResponseStatus(HttpStatus.OK)
    public CityResponse getCityLocationById(@PathVariable Long idCity) {
        return getCityLocationByIdService.find(idCity);
    }

}
