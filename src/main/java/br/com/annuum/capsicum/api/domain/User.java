package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.Profile;

public interface User extends HasEncodedPassword {

    Long getId();

    String getName();

    String getEmail();

    String getPassword();

    Profile getProfile();
}
