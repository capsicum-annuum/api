package br.com.annuum.capsicum.api.controller.request;

import br.com.annuum.capsicum.api.domain.enums.DayShift;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.DayOfWeek;

@Data
@Accessors(chain = true)
public class DayShiftAvailabilityRequest {

    private DayOfWeek dayOfWeek;

    private DayShift dayShift;

}
