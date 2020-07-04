package br.com.annuum.capsicum.api.controller.response;

import br.com.annuum.capsicum.api.domain.dto.CandidacyDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class CandidacyListResponse {

    private List<CandidacyDto> candidacies;

}
