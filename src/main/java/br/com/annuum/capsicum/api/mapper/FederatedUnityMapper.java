package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.domain.FederatedUnity;
import br.com.annuum.capsicum.api.domain.dto.FederatedUnityDto;
import org.springframework.stereotype.Component;

@Component
public class FederatedUnityMapper implements Mapper<FederatedUnity, FederatedUnityDto> {

    @Override
    public FederatedUnityDto map(FederatedUnity federatedUnity) {
        FederatedUnityDto response = new FederatedUnityDto();
        response.setId(federatedUnity.getId());
        response.setAcronym(federatedUnity.getAcronym());
        response.setName(federatedUnity.getName());
        return response;
    }

}
