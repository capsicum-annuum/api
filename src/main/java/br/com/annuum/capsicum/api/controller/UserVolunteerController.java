package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UserVolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.controller.request.UserVolunteerRequest;
import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.ClearImageRepoService;
import br.com.annuum.capsicum.api.service.SaveUserVolunteerEvaluationService;
import br.com.annuum.capsicum.api.service.SaveUserVolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-volunteer")
public class UserVolunteerController {

    @Autowired
    private SaveUserVolunteerService saveUserVolunteerService;

    @Autowired
    private SaveUserVolunteerEvaluationService saveUserVolunteerEvaluationService;

    @Autowired
    private ClearImageRepoService clearImageRepoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserVolunteerResponse saveUserVolunteer(@Valid @RequestBody final UserVolunteerRequest userVolunteerRequest) {
        return clearImageRepoService.clear(userVolunteerRequest.getProfilePictureKey(),
            () -> saveUserVolunteerService.save(userVolunteerRequest)
        );
    }

    @PostMapping("/evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUserVolunteerEvaluation(@AuthenticationPrincipal final UserPrincipal currentUser,
                                            @Valid @RequestBody final UserVolunteerEvaluationRequest userVolunteerEvaluationRequest) {
        saveUserVolunteerEvaluationService.save(currentUser.getId(), userVolunteerEvaluationRequest);
    }

}
