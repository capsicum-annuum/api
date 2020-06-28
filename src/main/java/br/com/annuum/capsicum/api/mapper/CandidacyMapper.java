package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.response.UserVolunteerResponse;
import br.com.annuum.capsicum.api.domain.Candidacy;
import br.com.annuum.capsicum.api.domain.dto.CandidacyDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CandidacyMapper implements Mapper<Candidacy, CandidacyDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CandidacyDto map(Candidacy candidacy) {
        return new CandidacyDto()
            .setCandidacyStatus(candidacy.getCandidacyStatusControl().getStatusEnum())
            .setCreatedAt(candidacy.getCreatedAt())
            .setId(candidacy.getId())
            .setUserCandidate(modelMapper.map(candidacy.getUserCandidate(), UserVolunteerResponse.class));
    }

}
