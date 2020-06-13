package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.domain.dto.FederatedUnityDto;
import br.com.annuum.capsicum.api.domain.FederatedUnity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FederatedUnityMapper {

    public List<FederatedUnityDto> toFederatedUnityList(List<FederatedUnity> federatedUnities) {
        return federatedUnities.stream().map(federatedUnity -> toFederatedUnityResponse(federatedUnity)).collect(Collectors.toList());
    }

    public FederatedUnityDto toFederatedUnityResponse(FederatedUnity federatedUnity) {
        FederatedUnityDto response = new FederatedUnityDto();
        response.setId(federatedUnity.getId());
        response.setAcronym(federatedUnity.getAcronym());
        response.setName(federatedUnity.getName());
        return response;
    }
}
