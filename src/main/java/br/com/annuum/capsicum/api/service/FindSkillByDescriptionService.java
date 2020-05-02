package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindSkillByDescriptionService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill find(final String description) {
        log.info("Searching for Skill with description '{}'", description);
        return skillRepository.findByDescription(description)
            .orElseThrow(() -> new RegisterNotFoundException("Skill não encontrada!"));
    }
}
