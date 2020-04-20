package br.com.annuum.capsicum.api.listener;

import br.com.annuum.capsicum.api.domain.AbstractUser;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.utils.UserMatchCodeGeneratorUtils;

import javax.persistence.PrePersist;

public class UserListener {

    @PrePersist
    void preCreate(AbstractUser user) {
        if (user instanceof UserVolunteer) {
            UserVolunteer userVolunteer = (UserVolunteer) user;
            userVolunteer.setMatchCode(UserMatchCodeGeneratorUtils.encode(userVolunteer.getUserSkills(), userVolunteer.getCauseThatSupport()));
        }

    }
}
