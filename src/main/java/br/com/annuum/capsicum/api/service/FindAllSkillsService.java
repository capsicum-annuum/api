package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.dto.SkillDto;
import br.com.annuum.capsicum.api.repository.SkillRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FindAllSkillsService {

    @Autowired
    private SkillRepository skillRepository;

    public List<SkillDto> find() {
        log.info("Searching for all Skills.");
        return skillRepository.retrieveAllSkillsAsSkillsDto();
    }

}
