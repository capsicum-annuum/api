package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Movement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovementRepository extends PagingAndSortingRepository<Movement, Long> {

}
