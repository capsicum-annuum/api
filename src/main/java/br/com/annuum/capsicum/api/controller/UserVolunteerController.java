package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.service.SaveUserVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-volunteer")
public class UserVolunteerController {

    @Autowired
    private SaveUserVolunteerService saveUserVolunteerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void saveUserVolunteer(@RequestBody UserVolunteerRequest userVolunteerRequest) {
        saveUserVolunteerService.save(userVolunteerRequest);
    }
}
