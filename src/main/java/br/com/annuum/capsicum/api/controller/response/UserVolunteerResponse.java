package br.com.annuum.capsicum.api.controller.response;

import br.com.annuum.capsicum.api.domain.Availability;
import br.com.annuum.capsicum.api.domain.Cause;
import br.com.annuum.capsicum.api.domain.Skill;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserVolunteerResponse {

    private String name;

    private String email;

    private String description;

    private String profilePictureUrl;

    private List<Cause> causeThatSupport;

    private List<Skill> userSkills;

    private Availability availability;

    private String facebookUrl;

    private String instagramUrl;

    private String twitterUrl;
}
