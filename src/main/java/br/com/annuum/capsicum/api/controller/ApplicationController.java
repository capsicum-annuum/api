package br.com.annuum.capsicum.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController("/")
public class ApplicationController {

    @GetMapping("/guest")
    public ResponseEntity verifyGuest() {
        return ResponseEntity.ok().body("Hello, Guest!");
    }

    @GetMapping("/user")
    @RolesAllowed("USER")
    public ResponseEntity verifyUser() {
        return ResponseEntity.ok().body("Hello, User!");
    }

    @GetMapping("/group")
    @RolesAllowed("GROUP")
    public ResponseEntity verifyGroup() {
        return ResponseEntity.ok().body("Hello, Group!");
    }

    @GetMapping("/organization")
    @RolesAllowed("ORGANIZATION")
    public ResponseEntity verifyOrganization() {
        return ResponseEntity.ok().body("Hello, Organization!");
    }

    @GetMapping("/volunteer")
    @RolesAllowed("VOLUNTEER")
    public ResponseEntity verifyVolunteer() {
        return ResponseEntity.ok().body("Hello, Volunteer!");
    }

}
