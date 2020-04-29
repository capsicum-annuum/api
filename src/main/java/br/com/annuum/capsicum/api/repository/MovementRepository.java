package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Cause;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MovementRepository extends CrudRepository<Cause, Long> {

}
