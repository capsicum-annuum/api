package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.DayShift;
import java.time.DayOfWeek;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DayShiftAvailability {

  private DayOfWeek dayOfWeek;

  private DayShift dayShift;

}
