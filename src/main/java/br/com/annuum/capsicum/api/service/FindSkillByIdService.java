package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindSkillByIdService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill find(final Long id) {
        log.info("Searching for Skill with id '{}'", id);
        return skillRepository.findById(id)
            .orElseThrow(() -> new RegisterNotFoundException("Skill não encontrada!"));
    }
}
