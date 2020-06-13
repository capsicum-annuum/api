package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UniqueUserInformationRequest;
import br.com.annuum.capsicum.api.service.UniqueUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UniqueUserService service;

    @PostMapping("/validate")
    public void validate(@RequestBody UniqueUserInformationRequest request) {
        service.validate(request);
    }

}
