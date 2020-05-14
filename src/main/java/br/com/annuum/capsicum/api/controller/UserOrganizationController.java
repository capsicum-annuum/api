package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UserOrganizationEvaluationRequest;
import br.com.annuum.capsicum.api.controller.request.UserOrganizationRequest;
import br.com.annuum.capsicum.api.controller.response.UserOrganizationResponse;
import br.com.annuum.capsicum.api.service.SaveUserOrganizationEvaluationService;
import br.com.annuum.capsicum.api.service.SaveUserOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-organization")
public class UserOrganizationController {

    @Autowired
    private SaveUserOrganizationService saveUserOrganizationService;

    @Autowired
    private SaveUserOrganizationEvaluationService saveUserOrganizationEvaluationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserOrganizationResponse saveUserOrganization(@RequestBody @Valid final UserOrganizationRequest userOrganizationRequest) {
        return saveUserOrganizationService.save(userOrganizationRequest);
    }

    @PostMapping("/evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUserOrganizationEvaluation(@Valid @RequestBody final UserOrganizationEvaluationRequest userOrganizationEvaluationRequest) {
        saveUserOrganizationEvaluationService.save(userOrganizationEvaluationRequest);
    }

}
