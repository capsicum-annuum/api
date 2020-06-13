package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.response.FederatedUnityResponse;
import br.com.annuum.capsicum.api.service.FindAllFederatedUnityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/locality")
public class LocalityController {

    @Autowired
    private FindAllFederatedUnityService findAllFederatedUnityService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<FederatedUnityResponse> findAllFederatedUnities(){
        return findAllFederatedUnityService.find();
    }

}
