package br.com.annuum.capsicum.api.controller.response;

import br.com.annuum.capsicum.api.domain.dto.FederatedUnityDto;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FederatedUnitiesListResponse {

    List<FederatedUnityDto> federatedUnities;

}
