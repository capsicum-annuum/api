package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Candidacy;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidacyRepository extends CrudRepository<Candidacy, Long> {

    List<Candidacy> findAllByNeedId(Long needId);

}
