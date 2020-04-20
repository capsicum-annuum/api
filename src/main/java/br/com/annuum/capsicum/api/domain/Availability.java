package br.com.annuum.capsicum.api.domain;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Availability {

  private List<DayShiftAvailability> dayShiftAvailabilities;

}
