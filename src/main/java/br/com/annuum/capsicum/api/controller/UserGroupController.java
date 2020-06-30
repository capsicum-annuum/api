package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UserGroupEvaluationRequest;
import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.ClearImageRepoService;
import br.com.annuum.capsicum.api.service.SaveUserGroupEvaluationService;
import br.com.annuum.capsicum.api.service.SaveUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-group")
public class UserGroupController {

    @Autowired
    private SaveUserGroupService saveUserGroupService;

    @Autowired
    private SaveUserGroupEvaluationService saveUserGroupEvaluationService;

    @Autowired
    private ClearImageRepoService clearImageRepoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroupResponse saveUserGroup(@RequestBody @Valid final UserGroupRequest userGroupRequest) {
        return clearImageRepoService.clear(userGroupRequest.getProfilePictureKey(),
            () -> saveUserGroupService.save(userGroupRequest)
        );
    }

    @PostMapping("/evaluation")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUserGroupEvaluation(@AuthenticationPrincipal final UserPrincipal currentUser,
                                        @Valid @RequestBody final UserGroupEvaluationRequest userGroupEvaluationRequest) {
        saveUserGroupEvaluationService.save(currentUser.getId(), userGroupEvaluationRequest);
    }

}
