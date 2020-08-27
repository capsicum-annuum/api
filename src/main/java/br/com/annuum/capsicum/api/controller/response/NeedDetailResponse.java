package br.com.annuum.capsicum.api.controller.response;

import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NeedDetailResponse {

    private Long id;

    private String skill;

    private String description;

    private CandidacyStatus status;

    private Integer totalVacancies;

    private Integer occupiedVacancies;

}
