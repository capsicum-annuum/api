package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.CandidacyStatus;
import br.com.annuum.capsicum.api.exceptions.StatusTransitionExcepetion;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Embeddable
public class CandidacyStatusControl {

    @Enumerated(EnumType.STRING)
    private CandidacyStatus statusEnum;

    public CandidacyStatusControl() {
        statusEnum = CandidacyStatus.CANDIDATE;
    }

    public void setStatusEnum(CandidacyStatus statusEnum) {
        if (this.statusEnum.getNextStatusSupported().contains(statusEnum.name())) {
            this.statusEnum = statusEnum;
        } else {
            throw new StatusTransitionExcepetion(String.format("Não é possível alterar o status da candidatura de %s para %s",
                this.statusEnum.name(),
                statusEnum.name()));
        }
    }

}
