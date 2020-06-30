package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class UserGroupEvaluationRequest {

    @NotNull
    private Long candidacyId;

    @NotNull
    private Integer note;

    private String feedback;

}
