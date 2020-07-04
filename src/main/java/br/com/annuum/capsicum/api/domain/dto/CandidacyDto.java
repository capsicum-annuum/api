package br.com.annuum.capsicum.api.domain.dto;

import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.UserVolunteer;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CandidacyDto {

    private Long id;

    private Long idNeed;

    private UserVolunteerResponse userCandidate;

    private CandidacyStatus candidacyStatus;

    private LocalDateTime createdAt;

}
