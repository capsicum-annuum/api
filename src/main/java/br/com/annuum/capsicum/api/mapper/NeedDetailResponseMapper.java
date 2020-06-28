package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.response.NeedDetailResponse;
import br.com.annuum.capsicum.api.domain.Need;
import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import org.springframework.stereotype.Component;

@Component
public class NeedDetailResponseMapper implements Mapper<Need, NeedDetailResponse> {

    @Override
    public NeedDetailResponse map(Need need) {

        final Integer totalApproved = Math.toIntExact(need.getCandidacies().stream()
            .filter(c -> c.getCandidacyStatusControl().getStatusEnum().equals(CandidacyStatus.APPROVED))
            .count()); // TODO: colocar essa regra na service que busca o feed quando houver

        return new NeedDetailResponse()
            .setId(need.getId())
            .setDescription(need.getDescription())
            .setSkill(need.getSkill().getName())
            .setStatus(need.getCandidacies().get(0).getCandidacyStatusControl().getStatusEnum()) // TODO: from feed query
            .setTotalVacancies(need.getQuantity())
            .setOccupiedVacancies(need.getQuantity() - totalApproved);
    }

}
