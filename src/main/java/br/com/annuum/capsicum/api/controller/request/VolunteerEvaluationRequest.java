package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class VolunteerEvaluationRequest {

    @NotNull
    private Long idCandidacy;

    @NotNull
    private Integer note;

    private String feedback;
}
