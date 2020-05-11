package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.Movement;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UserOrganizationEvaluationRequest {

    @NotNull
    private Long userVolunteerEvaluatorId;

    @NotNull
    private Long userOrganizationEvaluatedId;

    @NotNull
    private Long movementId;

    @NotNull
    private Integer note;

    private String feedback;
}
