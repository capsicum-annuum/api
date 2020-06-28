package br.com.annuum.capsicum.api.controller.response;

import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CandidacyResponse {

    private Long needId;

    private CandidacyStatus candidacyStatus;

    private LocalDateTime createdAt;

}
