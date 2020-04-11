package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.UserGroupRequest;
import br.com.annuum.capsicum.api.controller.response.UserGroupResponse;
import br.com.annuum.capsicum.api.service.SaveUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user-group")
public class UserGroupController {

    @Autowired
    private SaveUserGroupService saveUserGroupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserGroupResponse saveUserGroup(@RequestBody @Valid final UserGroupRequest userGroupRequest) {
        return saveUserGroupService.save(userGroupRequest);
    }
}
