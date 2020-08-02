package br.com.annuum.capsicum.api.validator;

import br.com.annuum.capsicum.api.exceptions.EvaluationDebitsException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovementAuthorEvaluationDebitsValidator {

    @Autowired
    private CandidacyRepository candidacyRepository;

    public void validate(Long idMovementAuthor) {
        if (!candidacyRepository
            .findCandidaciesWithEvaluationDebitsOfMovementAuthor(idMovementAuthor)
            .isEmpty()) {
            throw new EvaluationDebitsException("O usuário possui avaliações pendentes.");
        }
    }
}
