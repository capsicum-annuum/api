package br.com.annuum.capsicum.api.utils;

import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Skill;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.List;

public class UserMatchCodeGeneratorUtils {

    public static String encode(@Nullable List<Skill> skills, @NotNull List<Cause> causes) {
        StringBuilder builder = new StringBuilder();
        skills.forEach(skill -> {
            builder.append(Integer.toBinaryString(skill.getBinaryIdentifier()));
        });
        builder.append("_");
        causes.forEach(cause -> {
            builder.append(Integer.toBinaryString(cause.getBinaryIdentifier()));
        });
        return builder.toString();
    }

}
