package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.Profile;

public interface User {

    Long getId();

    String getName();

    String getEmail();

    String getPassword();

    Profile getProfile();
}
