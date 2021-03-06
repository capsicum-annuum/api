package br.com.annuum.capsicum.api.controller;

import br.com.annuum.capsicum.api.controller.response.SkillListResponse;
import br.com.annuum.capsicum.api.service.FindAllSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private FindAllSkillsService findAllSkillsService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public SkillListResponse findAllSkills() {
        return findAllSkillsService.find();
    }

}
