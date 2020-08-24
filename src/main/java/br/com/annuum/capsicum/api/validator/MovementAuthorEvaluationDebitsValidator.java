package br.com.annuum.capsicum.api.validator;

import br.com.annuum.capsicum.api.exceptions.PendingEvaluationException;
import br.com.annuum.capsicum.api.repository.CandidacyRepository;
import br.com.annuum.capsicum.api.service.FindMovementByNeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovementAuthorEvaluationDebitsValidator {

    @Autowired
    private CandidacyRepository candidacyRepository;

    @Autowired
    private FindMovementByNeedService findMovementByNeedService;

    public void validate(Long idMovementAuthor) {
        candidacyRepository.findCandidaciesWithEvaluationDebitsOfMovementAuthor(idMovementAuthor)
            .stream()
            .findFirst()
            .ifPresent(candidacy -> {
                throw new PendingEvaluationException(
                    String.format("O usuário possui avaliações pendentes para o movimento de id %s",
                        findMovementByNeedService.find(candidacy.getNeed()).getId()));
            });
    }
}
