package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.SaveCandidacyService;
import br.com.annuum.capsicum.api.service.UpdateCandidacyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public void saveCandidacy(@AuthenticationPrincipal final UserPrincipal currentUser, @RequestParam final Long idNeed) {
        saveCandidacyService.save(currentUser.getId(), idNeed);
    }

    @PutMapping("update/{idCandidacy}/candidate")
    @ResponseStatus(HttpStatus.OK)
    public void setCandidacyStatusCandidate(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.CANDIDATE);
    }

    @PutMapping("update/{idCandidacy}/rejected")
    @ResponseStatus(HttpStatus.OK)
    public void setCandidacyStatusRejected(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.REJECTED);
    }

    @PutMapping("update/{idCandidacy}/approved")
    @ResponseStatus(HttpStatus.OK)
    public void setCandidacyStatusApproved(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.APPROVED);
    }

    @PutMapping("update/{idCandidacy}/declined")
    @ResponseStatus(HttpStatus.OK)
    public void setCandidacyStatusDeclined(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.DECLINED);
    }

    @PutMapping("update/{idCandidacy}/present")
    @ResponseStatus(HttpStatus.OK)
    public void setCandidacyStatusPresent(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.PRESENT);
    }

    @PutMapping("update/{idCandidacy}/absent")
    @ResponseStatus(HttpStatus.OK)
    public void setCandidacyStatusAbsent(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.ABSENT);
    }

}
