package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.VolunteerEvaluation;
import org.springframework.data.repository.CrudRepository;

public interface VolunteerEvaluationRepository extends CrudRepository<VolunteerEvaluation, Long> {

    Boolean existsByCandidacyId(Long candidacyId);
}
