package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.MovementEvaluation;
import org.springframework.data.repository.CrudRepository;

public interface MovementEvaluationRepository extends CrudRepository<MovementEvaluation, Long> {

    Boolean existsByCandidacyId(Long candidacyId);
}
