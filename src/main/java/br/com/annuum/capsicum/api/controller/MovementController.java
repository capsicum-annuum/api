package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.MovementRequest;
import br.com.annuum.capsicum.api.controller.response.MovementResponse;
import br.com.annuum.capsicum.api.service.SaveMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private SaveMovementService saveMovementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovementResponse saveMovement(@Valid @RequestBody final MovementRequest movementRequest) {
        return saveMovementService.save(movementRequest);
    }

}
