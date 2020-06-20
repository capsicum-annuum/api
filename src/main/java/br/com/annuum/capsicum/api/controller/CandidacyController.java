package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.request.CandidacyRequest;
import br.com.annuum.capsicum.api.service.SaveCandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/candidacy")
public class CandidacyController {

    @Autowired
    private SaveCandidacyService saveCandidacyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCandidacy(@Valid @RequestBody final CandidacyRequest candidacyRequest) {
        saveCandidacyService.save(candidacyRequest);
    }

}
