package br.com.annuum.capsicum.api.mapper;

import br.com.annuum.capsicum.api.controller.request.AvailabilityRequest;
import br.com.annuum.capsicum.api.domain.Availability;
import br.com.annuum.capsicum.api.domain.DayShiftAvailability;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Component
public class AvailabilityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Availability map(AvailabilityRequest availabilityRequest) {
        final Availability availability = new Availability();
        if (nonNull(availabilityRequest)) {
            availability.setDayShiftAvailabilities(availabilityRequest.getDayShiftAvailabilities()
                .stream()
                .map(dayShft -> modelMapper.map(dayShft, DayShiftAvailability.class))
                .collect(Collectors.toList()));
        }
        return availability;
    }


}
