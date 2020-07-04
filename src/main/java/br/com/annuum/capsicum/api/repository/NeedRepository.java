package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.domain.dto.CandidacyDto;
import br.com.annuum.capsicum.api.domain.dto.CityDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NeedRepository extends CrudRepository<Need, Long> {

}
