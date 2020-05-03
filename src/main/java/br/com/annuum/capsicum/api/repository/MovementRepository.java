package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Movement;
import org.springframework.data.repository.CrudRepository;

public interface MovementRepository extends CrudRepository<Movement, Long> {

}
