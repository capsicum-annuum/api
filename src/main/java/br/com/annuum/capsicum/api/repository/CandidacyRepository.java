package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Need;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CandidacyRepository extends CrudRepository<Candidacy, Long> {

    List<Candidacy> findAllByNeed(Need need);

}
