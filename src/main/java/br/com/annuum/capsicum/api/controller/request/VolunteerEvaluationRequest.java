package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class VolunteerEvaluationRequest {

    @NotNull
    private Long idCandidacy;

    @NotNull
    @Min(value = 1, message = "A nota mínima deve ser 1")
    @Max(value = 5, message = "A nota máxima deve ser 5")
    private Integer rate;

    @Nullable
    @Size(max = 200)
    private String feedback;
}
