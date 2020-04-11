package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Cause;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CauseRepository extends CrudRepository<Cause, Long> {

    List<Cause> findAll();

    Optional<Cause> findByDescription(String description);

    boolean existsByDescriptionIgnoringCase(final String cause);
    
}
