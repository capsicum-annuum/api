package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.controller.response.SkillListResponse;
import br.com.annuum.capsicum.api.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FindAllSkillsService {

    @Autowired
    private SkillRepository skillRepository;

    public SkillListResponse find() {
        log.info("Searching for all Skills.");
        return new SkillListResponse()
            .setSkillList(skillRepository.retrieveAllSkillsAsSkillsDto());
    }

}
