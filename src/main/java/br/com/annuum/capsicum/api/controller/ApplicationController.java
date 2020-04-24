package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.domain.SpatialLocation;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.ApplicationService;
import java.util.Optional;

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
    @RolesAllowed("USER")
    public ResponseEntity verifyUser(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

    @GetMapping("/group")
    @RolesAllowed("GROUP")
    public ResponseEntity verifyGroup(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

    @GetMapping("/organization")
    @RolesAllowed("ORGANIZATION")
    public ResponseEntity verifyOrganization(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

    @GetMapping("/volunteer")
    @RolesAllowed("VOLUNTEER")
    public ResponseEntity verifyVolunteer(@AuthenticationPrincipal final UserPrincipal currentUser) {
        return ResponseEntity.ok().body(service.greetings(of(currentUser)));
    }

}
