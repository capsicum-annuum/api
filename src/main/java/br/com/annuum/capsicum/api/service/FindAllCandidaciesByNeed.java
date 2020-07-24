package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.CandidacyListResponse;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.mapper.CandidacyMapper;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindAllCandidaciesByNeed {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private FindNeedByIdService findNeedByIdService;

    @Autowired
    private FindMovementByNeedService findMovementByNeedService;

    @Autowired
    private CandidacyMapper candidacyMapper;

    public CandidacyListResponse find(final Long idUser, final Long idNeed) {
        log.info("Searching for all Candidacies with Need id: '{}'", idNeed);
        final Need need = findNeedByIdService.find(idNeed);

        if (!findMovementByNeedService.find(need).getUserAuthor().getId().equals(idUser)) {
            throw new AccessControlException(
                "O usuário autenticado não é o administrador do movimento.");
        }
        return new CandidacyListResponse()
            .setCandidacies(candidacyMapper.map(candidacyRepository.findAllByNeed(need)));
    }

}
