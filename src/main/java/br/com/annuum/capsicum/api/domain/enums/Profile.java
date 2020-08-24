package br.com.annuum.capsicum.api.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

import static java.util.Arrays.asList;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Profile {

    GROUP(asList(
        role(Names.USER),
        role(Names.GROUP))),

    ORGANIZATION(asList(
        role(Names.USER),
        role(Names.ORGANIZATION))),

    VOLUNTEER(asList(
        role(Names.USER),
        role(Names.VOLUNTEER)));

    private final Collection<String> roles;

    public String toRoles() {
        return String.join(", ", roles);
    }

    public static class Names {
        public static final String USER = "USER";
        public static final String GROUP = "GROUP";
        public static final String ORGANIZATION = "ORGANIZATION";
        public static final String VOLUNTEER = "VOLUNTEER";
    }

    private static String role(final String profile) {
        return "ROLE_" + profile;
    }
}
