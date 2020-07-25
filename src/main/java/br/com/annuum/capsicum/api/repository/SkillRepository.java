package br.com.annuum.capsicum.api.repository;

import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.domain.dto.SkillDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SkillRepository extends CrudRepository<Skill, Long> {

    Optional<Skill> findByDescription(String description);

    @Query("SELECT new br.com.annuum.capsicum.api.domain.dto.SkillDto(s.id, s.description) FROM Skill s")
    List<SkillDto> retrieveAllSkillsAsSkillsDto();

}
