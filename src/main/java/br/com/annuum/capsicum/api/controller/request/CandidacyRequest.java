package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class CandidacyRequest {

    @NotNull(message = "O id do usuário voluntário não pode ser nulo.")
    private Long userVolunteerId;

    @NotNull(message = "O id da necessidade não pode ser nula.")
    private Long needId;

}
