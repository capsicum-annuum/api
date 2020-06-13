package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.response.FederatedUnityResponse;
import br.com.annuum.capsicum.api.domain.FederatedUnity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FederatedUnityMapper {

    public List<FederatedUnityResponse> toFederatedUnityList(List<FederatedUnity> federatedUnities) {
        return federatedUnities.stream().map(federatedUnity -> toFederatedUnityResponse(federatedUnity)).collect(Collectors.toList());
    }

    public FederatedUnityResponse toFederatedUnityResponse(FederatedUnity federatedUnity) {
        FederatedUnityResponse response = new FederatedUnityResponse();
        response.setId(federatedUnity.getId());
        response.setAcronym(federatedUnity.getAcronym());
        response.setName(federatedUnity.getName());
        return response;
    }
}
