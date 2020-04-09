package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Skill;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SkillRepository extends PagingAndSortingRepository<Skill, Long> {

    Optional<Skill> findByDescription(String description);
}
