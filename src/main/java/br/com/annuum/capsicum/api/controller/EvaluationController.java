package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.MovementEvaluationRequest;
import br.com.annuum.capsicum.api.controller.request.VolunteerEvaluationRequest;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.SaveMovementEvaluationService;
import br.com.annuum.capsicum.api.service.SaveVolunteerEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    @Autowired
    private SaveVolunteerEvaluationService saveVolunteerEvaluationService;

    @Autowired
    private SaveMovementEvaluationService saveMovementEvaluationService;

    @PostMapping("/movement")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveMovementEvaluation(@AuthenticationPrincipal final UserPrincipal currentUser,
                                       @Valid @RequestBody final MovementEvaluationRequest movementEvaluationRequest) {
        saveMovementEvaluationService.save(currentUser.getId(), movementEvaluationRequest);
    }

    @PostMapping("/volunteer")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUserVolunteerEvaluation(@AuthenticationPrincipal final UserPrincipal currentUser,
                                            @Valid @RequestBody final VolunteerEvaluationRequest volunteerEvaluationRequest) {
        saveVolunteerEvaluationService.save(currentUser.getId(), volunteerEvaluationRequest);
    }

}
