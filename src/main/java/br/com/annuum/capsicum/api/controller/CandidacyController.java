package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.SaveCandidacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidacy")
public class CandidacyController {

    @Autowired
    private SaveCandidacyService saveCandidacyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCandidacy(@AuthenticationPrincipal final UserPrincipal currentUser, @RequestParam final Long idNeed) {
        saveCandidacyService.save(currentUser.getId(), idNeed);
    }

}
