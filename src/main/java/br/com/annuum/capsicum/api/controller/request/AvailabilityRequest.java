package br.com.annuum.capsicum.api.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Accessors(chain = true)
public class AvailabilityRequest {

    @NotEmpty(message = "A lista de Disponibilidades não pode estar vazia.")
    private List<DayShiftAvailabilityRequest> dayShiftAvailabilities;

}
