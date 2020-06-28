package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

@RestController("/")
public class ApplicationController {

    @Autowired
    private ApplicationService service;

    @GetMapping("/guest")
    public ResponseEntity verifyGuest(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(ofNullable(currentUser)));
    }

    @GetMapping("/user")
    @RolesAllowed(Profile.Names.USER)
    public ResponseEntity verifyUser(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

    @GetMapping("/group")
    @RolesAllowed(Profile.Names.GROUP)
    public ResponseEntity verifyGroup(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

    @GetMapping("/organization")
    @RolesAllowed(Profile.Names.ORGANIZATION)
    public ResponseEntity verifyOrganization(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

    @GetMapping("/volunteer")
    @RolesAllowed(Profile.Names.VOLUNTEER)
    public ResponseEntity verifyVolunteer(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

}
