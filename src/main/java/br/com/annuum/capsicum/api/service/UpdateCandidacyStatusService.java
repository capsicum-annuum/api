package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.StatusTransitionExcepetion;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.table.TableStringConverter;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UpdateCandidacyStatusService {

    @Autowired
    private FindUserVolunteerByIdService findUserVolunteerByIdService;

    @Autowired
    private FindCandidacyByIdService findCandidacyByIdService;

    @Autowired
    private FindMovimentByNeedService findMovimentByNeedService;

    @Autowired
    private CandidacyRepository candidacyRepository;

    private static final List<CandidacyStatus> STATUS_THAT_REQUIRE_A_CONCLUDE_MOVEMENT =
        Arrays.asList(
            CandidacyStatus.ABSENT,
            CandidacyStatus.PRESENT);

    @Transactional
    public Candidacy update(final Long idCandidacy, CandidacyStatus candidacyStatus) {

        log.info("Start to update Candidacy with id: '{}'", idCandidacy);
        final Candidacy candidacy = findCandidacyByIdService.find(idCandidacy);

        final Movement movement = findMovimentByNeedService.find(candidacy.getNeed());

        if (STATUS_THAT_REQUIRE_A_CONCLUDE_MOVEMENT.contains(candidacyStatus)
            && !movement.getMovementStatus().equals(MovementStatus.CONCLUDE)) {
            throw new StatusTransitionExcepetion(
                String.format("Não é possível alterar para o Status '{}' enquanto o Movimento não estiver concluído.", candidacyStatus));
        }


    }

}
