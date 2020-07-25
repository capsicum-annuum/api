package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UniqueUserInformationRequest;
import br.com.annuum.capsicum.api.service.UniqueUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UniqueUserService service;

    @PostMapping("/validate")
    public void validate(@RequestBody @Valid final UniqueUserInformationRequest request) {
        service.validate(request);
    }

}
