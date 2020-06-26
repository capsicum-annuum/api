package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.StatusUpdateNotAllowedException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.List;

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
                "O usuário autenticado não é o administrador do Movimento.");
        }

        if (movement.getMovementStatus().equals(MovementStatus.CANCELLED)) {
            throw new StatusUpdateNotAllowedException(
                "Não é possível alterar o status da candidatura quando o movimento está cancelado.");
        }

        final List<CandidacyStatus> STATUS_ACCEPTABLE_BY_CONCLUDE_MOVEMENT =
            Arrays.asList(
                CandidacyStatus.ABSENT,
                CandidacyStatus.PRESENT);

        if (movement.getMovementStatus().equals(MovementStatus.ACTIVE)
            && STATUS_ACCEPTABLE_BY_CONCLUDE_MOVEMENT.contains(candidacyStatus)) {
            throw new StatusUpdateNotAllowedException(
                String.format("Não é possível alterar o status da candidatura para %s enquanto o Movimento não estiver concluído.", candidacyStatus));
        }

        if (movement.getMovementStatus().equals(MovementStatus.CONCLUDE)
            && !STATUS_ACCEPTABLE_BY_CONCLUDE_MOVEMENT.contains(candidacyStatus)) {
            throw new StatusUpdateNotAllowedException(
                String.format("Não é possível alterar o status da candidatura para %s com o movimento concluído", candidacyStatus));
        }

        candidacy.getCandidacyStatusControl().setStatusEnum(candidacyStatus);
        return candidacyRepository.save(candidacy);
    }

}
