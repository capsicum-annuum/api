package br.com.annuum.capsicum.api.controller.request;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AvailabilityRequest {

  @NotEmpty(message = "A lista de Disponibilidades não pode estar vazia.")
  private List<DayShiftAvailabilityRequest> dayShiftAvailabilities;

}
