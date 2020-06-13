package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.dto.CauseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CauseRepository extends CrudRepository<Cause, Long> {

    List<Cause> findAll();

    Optional<Cause> findByDescription(String description);

    @Query("SELECT new br.com.annuum.capsicum.api.domain.dto.CauseDto(c.id, c.description) FROM Cause c")
    List<CauseDto> retrieveAllCausesAsCausesDto();

}
