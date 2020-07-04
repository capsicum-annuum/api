package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.response.CandidacyListResponse;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.Profile;
import br.com.annuum.capsicum.api.security.UserPrincipal;
import br.com.annuum.capsicum.api.service.FindAllCandidaciesByNeed;
import br.com.annuum.capsicum.api.service.SaveCandidacyService;
import br.com.annuum.capsicum.api.service.UpdateCandidacyStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/candidacy")
public class CandidacyController {

    @Autowired
    private SaveCandidacyService saveCandidacyService;

    @Autowired
    private UpdateCandidacyStatusService updateCandidacyStatusService;

    @Autowired
    private FindAllCandidaciesByNeed findAllCandidaciesByNeed;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(Profile.Names.VOLUNTEER)
    public void saveCandidacy(@AuthenticationPrincipal final UserPrincipal currentUser, @RequestParam final Long idNeed) {
        saveCandidacyService.save(currentUser.getId(), idNeed);
    }

    @PatchMapping("{idCandidacy}/candidate")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Profile.Names.USER)
    public void setCandidacyStatusCandidate(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable final Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.CANDIDATE);
    }

    @PatchMapping("{idCandidacy}/reject")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Profile.Names.USER)
    public void setCandidacyStatusRejected(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable final Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.REJECTED);
    }

    @PatchMapping("{idCandidacy}/approve")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Profile.Names.USER)
    public void setCandidacyStatusApproved(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable final Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.APPROVED);
    }

    @PatchMapping("{idCandidacy}/decline")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Profile.Names.USER)
    public void setCandidacyStatusDeclined(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable final Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.DECLINED);
    }

    @PatchMapping("{idCandidacy}/present")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Profile.Names.USER)
    public void setCandidacyStatusPresent(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable final Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.PRESENT);
    }

    @PatchMapping("{idCandidacy}/absent")
    @ResponseStatus(HttpStatus.OK)
    @RolesAllowed(Profile.Names.USER)
    public void setCandidacyStatusAbsent(@AuthenticationPrincipal final UserPrincipal currentUser, @PathVariable final Long idCandidacy) {
        updateCandidacyStatusService.update(currentUser.getId(), idCandidacy, CandidacyStatus.ABSENT);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed(Profile.Names.USER)
    public CandidacyListResponse findAllCandidaciesByNeed(@AuthenticationPrincipal final UserPrincipal currentUser, @RequestParam final Long idNeed) {
        return findAllCandidaciesByNeed.find(currentUser.getId(), idNeed);
    }

}
