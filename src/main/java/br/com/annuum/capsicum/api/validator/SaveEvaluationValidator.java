package br.com.annuum.capsicum.api.validator;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.domain.enums.MovementStatus;
import br.com.annuum.capsicum.api.exceptions.AccessControlException;
import org.springframework.stereotype.Component;

@Component
public class SaveEvaluationValidator {

    public void validate(Movement movement, Candidacy candidacy) {

        if (!movement.getMovementStatus().equals(MovementStatus.CONCLUDE)) {
            throw new AccessControlException("Não é possível gerar avaliações enquanto o Movimento não estiver concluído.");
        }

        if (!candidacy.getCandidacyStatusControl().getStatusEnum().equals(CandidacyStatus.PRESENT)) {
            throw new AccessControlException("O usuário voluntário possui status da candidatura diferente de PRESENT.");
        }
    }
}
