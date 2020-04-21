package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.ApiApplication;
import br.com.annuum.capsicum.api.component.PointFactory;
import br.com.annuum.capsicum.api.domain.MyPoint;
import br.com.annuum.capsicum.api.domain.SpatialLocation;
import br.com.annuum.capsicum.api.repository.MyPointRepository;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.ApplicationService;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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


    @Autowired
    private MyPointRepository repository;

    @Autowired
    private PointFactory pointFactory;

    @GetMapping()
    public void setup() {

        MyPoint point = new MyPoint()
//            .setPoint(pointFactory.createPointFromSpatialLocation(new SpatialLocation()
//                .setLatitude(50.15)
//                .setLongitude(15.50)))
            .setSpatialLocation(new SpatialLocation()
                .setLatitude(50.15)
                .setLongitude(15.50))
            ;

        repository.save(point);


        Optional<MyPoint> fromDb = repository.findById(1L);
        System.out.println(fromDb);

        point.setSpatialLocation(new SpatialLocation()
            .setLatitude(10.4)
            .setLongitude(4.10));


        repository.save(point);


        Optional<MyPoint> fromDb2 = repository.findById(1L);
        System.out.println(fromDb2);

    }

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
