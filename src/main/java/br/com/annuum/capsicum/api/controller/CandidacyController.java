package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.service.SaveCandidacyService;
import br.com.annuum.capsicum.api.service.UpdateCandidacyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidacy")
public class CandidacyController {

    @Autowired
    private SaveCandidacyService saveCandidacyService;

    @Autowired
    private UpdateCandidacyStatusService updateCandidacyStatusService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCandidacy(@RequestParam final Long idUserAuthenticated, @RequestParam final Long idNeed) {
        saveCandidacyService.save(idUserAuthenticated, idNeed);
    }

    @PatchMapping("update/{idCandidacy}/approved")
    @ResponseStatus(HttpStatus.OK)
    public void setCandidacyStatusApproved(@RequestParam Long idUserAuthenticated, @PathVariable Long idCandidacy) {
        updateCandidacyStatusService.update(idUserAuthenticated, idCandidacy, CandidacyStatus.APPROVED);
    }
}
