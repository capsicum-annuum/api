package br.com.annuum.capsicum.api.service;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.toBinaryString;

@Service
public class CodifiyCauseAndSkillAndTimeAvailability {

    public String getCodification(List<Skill> skills, List<Cause> causes) {
        StringBuilder builder = new StringBuilder();
        skills.forEach(skill -> builder
                .append(toBinaryString(skill.getBinaryCode()))
        );
        builder.append("_");
        causes.forEach(cause -> builder
                .append(toBinaryString(cause.getBinaryCode()))
        );
        return builder.toString();
    }
}
