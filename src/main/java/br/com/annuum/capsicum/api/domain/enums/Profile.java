package br.com.annuum.capsicum.api.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

import static java.util.Arrays.asList;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Profile {

    GROUP(asList("ROLE_USER", "ROLE_GROUP")),
    ORGANIZATION(asList("ROLE_USER", "ROLE_ORGANIZATION")),
    VOLUNTEER(asList("ROLE_USER", "ROLE_VOLUNTEER"));

    private final Collection<String> roles;

    public String toRoles() {
        return String.join(", ", roles);
    }
}
