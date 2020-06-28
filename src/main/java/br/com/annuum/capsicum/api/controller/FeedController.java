package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.response.MovementSimpleResponse;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService service;

    @GetMapping
    @RolesAllowed(Profile.Names.USER)
    public Page<MovementSimpleResponse> feed(
        @AuthenticationPrincipal final UserPrincipal currentUser,
        @PageableDefault Pageable pageable) {
        return service.list(currentUser.getId(), pageable);
    }

}
