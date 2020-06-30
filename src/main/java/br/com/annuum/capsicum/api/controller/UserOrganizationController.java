package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UserOrganizationEvaluationRequest;
import br.com.annuum.capsicum.api.controller.request.UserOrganizationRequest;
import br.com.annuum.capsicum.api.controller.response.UserOrganizationResponse;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.ClearImageRepoService;
import br.com.annuum.capsicum.api.service.SaveUserOrganizationEvaluationService;
import br.com.annuum.capsicum.api.service.SaveUserOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-organization")
public class UserOrganizationController {

    @Autowired
    private SaveUserOrganizationService saveUserOrganizationService;

    @Autowired
    private SaveUserOrganizationEvaluationService saveUserOrganizationEvaluationService;

    @Autowired
    private ClearImageRepoService clearImageRepoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOrganizationResponse saveUserOrganization(@RequestBody @Valid final UserOrganizationRequest userOrganizationRequest) {
        return clearImageRepoService.clear(userOrganizationRequest.getProfilePictureKey(),
            () -> saveUserOrganizationService.save(userOrganizationRequest)
        );
    }

    @PostMapping("/evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUserOrganizationEvaluation(@AuthenticationPrincipal final UserPrincipal currentUser,
                                               @Valid @RequestBody final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest) {
        saveUserOrganizationEvaluationService.save(currentUser.getId(), userOrganizationEvaluationRequest);
    }

}
