package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import br.com.annuum.capsicum.api.exceptions.StatusUpdateNotAllowedException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class UpdateCandidacyStatusService {

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Autowired
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Transactional
    public Candidacy update(final Long idUserAuthenticated, final Long idCandidacy, CandidacyStatus candidacyStatus) {

        log.info("Start to update Candidacy Status with id: '{}'", idCandidacy);
        final Candidacy candidacy = findCandidacyByIdService.find(idCandidacy);

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        if (!movement.getUserAuthor().getId().equals(idUserAuthenticated)) {
            throw new AccessControlException(
                "O usuário autenticado não é o administrador do movimento.");
        }

        if (movement.getMovementStatus().equals(MovementStatus.CANCELLED)) {
            throw new StatusUpdateNotAllowedException(
                "Não é possível alterar o status da candidatura quando o movimento está cancelado.");
        }

        if (!movement.getMovementStatus().getAcceptableCandidacyStatusToSet().contains(candidacyStatus)) {
            throw new StatusUpdateNotAllowedException(
                String.format("Não é possível alterar o status da candidatura para %s com um movimento de status %s.",
                    candidacyStatus,
                    movement.getMovementStatus()));
        }

        candidacy.getCandidacyStatusControl().setStatusEnum(candidacyStatus);
        return candidacyRepository.save(candidacy);
    }

}
