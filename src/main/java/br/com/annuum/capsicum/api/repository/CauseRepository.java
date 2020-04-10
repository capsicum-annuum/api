package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Cause;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CauseRepository extends CrudRepository<Cause, Long> {

    Optional<Cause> findByDescription(String description);
}
