package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.service.ClearImageRepoService;
import br.com.annuum.capsicum.api.service.SaveUserVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-volunteer")
public class UserVolunteerController {

    @Autowired
    private SaveUserVolunteerService saveUserVolunteerService;

    @Autowired
    private ClearImageRepoService clearImageRepoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserVolunteerResponse saveUserVolunteer(@Valid @RequestBody final UserVolunteerRequest userVolunteerRequest) {
        return clearImageRepoService.clear(userVolunteerRequest.getPictureRequests(),
            () -> saveUserVolunteerService.save(userVolunteerRequest)
        );
    }
}
