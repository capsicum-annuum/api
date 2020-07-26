package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.MovementRequest;
import br.com.annuum.capsicum.api.controller.response.MovementResponse;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.ClearImageRepoService;
import br.com.annuum.capsicum.api.service.SaveMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movement")
public class MovementController {

    @Autowired
    private SaveMovementService saveMovementService;

    @Autowired
    private ClearImageRepoService clearImageRepoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovementResponse saveMovement(@AuthenticationPrincipal final UserPrincipal currentUser, @Valid @RequestBody final MovementRequest movementRequest) {
        return clearImageRepoService.clear(movementRequest.getPictureRequests(),
            () -> saveMovementService.save(currentUser.getId(), movementRequest)
        );
    }

}
