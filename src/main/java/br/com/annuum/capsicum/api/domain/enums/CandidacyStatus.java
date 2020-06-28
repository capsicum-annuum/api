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

    CANDIDATE(Arrays.asList("REJECTED", "APPROVED", "DECLINED")),
    REJECTED(Arrays.asList("CANDIDATE", "APPROVED")),
    APPROVED(Arrays.asList("CANDIDATE", "REJECTED", "DECLINED", "ABSENT", "PRESENT")),
    DECLINED(Arrays.asList("CANDIDATE", "APPROVED")),
    ABSENT(Arrays.asList("PRESENT", "APPROVED")),
    PRESENT(Arrays.asList("ABSENT", "APPROVED"));

    private List<String> nextStatusSupported;

}
