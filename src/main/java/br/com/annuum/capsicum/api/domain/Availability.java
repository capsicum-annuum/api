package br.com.annuum.capsicum.api.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class Availability {

    private List<DayShiftAvailability> dayShiftAvailabilities;

}
