package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.domain.enums.DayShift;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.DayOfWeek;

@Data
@Accessors(chain = true)
public class DayShiftAvailability {

    private DayOfWeek dayOfWeek;

    private DayShift dayShift;

}
