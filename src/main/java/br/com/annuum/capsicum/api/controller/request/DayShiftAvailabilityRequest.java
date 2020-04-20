package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.enums.DayShift;
import java.time.DayOfWeek;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DayShiftAvailabilityRequest {

  private DayOfWeek dayOfWeek;

  private DayShift dayShift;

}
