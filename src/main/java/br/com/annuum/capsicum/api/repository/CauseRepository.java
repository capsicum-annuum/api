package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Cause;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface CauseRepository extends PagingAndSortingRepository<Cause, Long> {

    Optional<Cause> findByDescription(String description);
}
