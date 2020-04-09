package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Skill;
import br.com.annuum.capsicum.api.exceptions.RegisterNotFoundException;
import br.com.annuum.capsicum.api.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindSkillByDescriptionService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill find(String description) {
        return skillRepository.findByDescription(description).orElseThrow(RegisterNotFoundException::new);
    }
}
