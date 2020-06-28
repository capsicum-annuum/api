package br.com.annuum.capsicum.api.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MovementStatus {
    ACTIVE(Arrays.asList(
        CandidacyStatus.CANDIDATE,
        CandidacyStatus.APPROVED,
        CandidacyStatus.DECLINED,
        CandidacyStatus.REJECTED)),
    CONCLUDE(Arrays.asList(
        CandidacyStatus.PRESENT,
        CandidacyStatus.ABSENT)),
    CANCELLED();

    private List<CandidacyStatus> acceptableCandidacyStatusToSet;
}
