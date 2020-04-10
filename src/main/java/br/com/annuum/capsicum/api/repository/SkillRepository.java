package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SkillRepository extends CrudRepository<Skill, Long> {

    Optional<Skill> findByDescription(String description);
}
