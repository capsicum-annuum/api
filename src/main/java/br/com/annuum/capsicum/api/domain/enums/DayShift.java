package br.com.annuum.capsicum.api.domain.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum DayShift {

    MORNING(1, "Manhã"),
    AFTERNOON(2, "Tarde"),
    NIGHT(3, "Noite");

    private int value;
    private String description;

    public static DayShift of(final int value) {
        for (DayShift dayShift : values()) {
            if (dayShift.getValue() == value) {
                return dayShift;
            }
        }
        return null;
    }
}
