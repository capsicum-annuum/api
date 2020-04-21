package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.LoginRequest;
import br.com.annuum.capsicum.api.controller.response.LoginResponse;
import br.com.annuum.capsicum.api.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationService service;

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest request) {
        return new LoginResponse()
                .setToken(service.authenticate(request.getUsername(), request.getPassword()));
    }

}
