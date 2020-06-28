package br.com.annuum.capsicum.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CandidacyStatus {

    CANDIDATE(Arrays.asList(
        Names.REJECTED,
        Names.APPROVED,
        Names.DECLINED)),
    REJECTED(Arrays.asList(
        Names.CANDIDATE,
        Names.APPROVED)),
    APPROVED(Arrays.asList(
        Names.CANDIDATE,
        Names.REJECTED,
        Names.DECLINED,
        Names.ABSENT,
        Names.PRESENT)),
    DECLINED(Arrays.asList(
        Names.CANDIDATE,
        Names.APPROVED)),
    ABSENT(Arrays.asList(
        Names.PRESENT,
        Names.APPROVED)),
    PRESENT(Arrays.asList(
        Names.ABSENT,
        Names.APPROVED));

    private List<String> nextStatusSupported;

    public static class Names {
        public static final String CANDIDATE = "CANDIDATE";
        public static final String REJECTED = "REJECTED";
        public static final String APPROVED = "APPROVED";
        public static final String DECLINED = "DECLINED";
        public static final String ABSENT = "ABSENT";
        public static final String PRESENT = "PRESENT";
    }

}
