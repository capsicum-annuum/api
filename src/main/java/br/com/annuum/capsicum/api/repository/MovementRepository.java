package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Movement;
import br.com.annuum.capsicum.api.domain.Need;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MovementRepository extends CrudRepository<Movement, Long> {

    Optional<Movement> findByNeedsContains(Need need);
}
