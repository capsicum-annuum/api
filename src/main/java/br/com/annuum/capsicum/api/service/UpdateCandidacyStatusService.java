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
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UpdateCandidacyStatusService {

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Autowired
    private FindMovementByNeedService findMovementByNeedService;

    @Autowired
    private CandidacyRepository candidacyRepository;

    private static final List<CandidacyStatus> STATUS_SETTABLE_BY_MOVEMENT_AUTHOR =
        Arrays.asList(
            CandidacyStatus.CANDIDATE,
            CandidacyStatus.APPROVED,
            CandidacyStatus.REJECTED,
            CandidacyStatus.ABSENT,
            CandidacyStatus.PRESENT);

    private static final List<CandidacyStatus> STATUS_SETTABLE_BY_CANDIDATE =
        Arrays.asList(
            CandidacyStatus.DECLINED,
            CandidacyStatus.CANDIDATE);

    @Transactional
    public Candidacy update(final Long idUserAuthenticated, final Long idCandidacy, CandidacyStatus candidacyStatus) {

        log.info("Start to update Candidacy Status with id: '{}'", idCandidacy);
        final Candidacy candidacy = findCandidacyByIdService.find(idCandidacy);

        final Movement movement = findMovementByNeedService.find(candidacy.getNeed());

        if (movement.getMovementStatus().equals(MovementStatus.CANCELLED)) {
            throw new StatusUpdateNotAllowedException(
                "Não é possível alterar o status da candidatura quando o movimento está cancelado.");
        }

        final boolean isMovementAuthor = movement.getUserAuthor().getId().equals(idUserAuthenticated);
        final boolean isCandidate = candidacy.getUserCandidate().getId().equals(idUserAuthenticated);

        if (!isMovementAuthor && !isCandidate) {
            throw new AccessControlException(
                "O usuário autenticado não possui autorização para alterar o Status desta Candidatura.");
        }

        if (isMovementAuthor &&
            (!STATUS_SETTABLE_BY_MOVEMENT_AUTHOR.contains(candidacyStatus) ||
                candidacy.getCandidacyStatusControl().getStatusEnum().equals(CandidacyStatus.DECLINED))) {
            throw new AccessControlException(
                "Alteração de Status restrita ao candidato.");
        }

        if (isCandidate && !STATUS_SETTABLE_BY_CANDIDATE.contains(candidacyStatus)) {
            throw new AccessControlException(
                "Alteração de Status restrita ao administrador do movimento.");
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
