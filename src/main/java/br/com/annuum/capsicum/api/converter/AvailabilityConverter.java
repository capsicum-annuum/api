package br.com.annuum.capsicum.api.converter;

import br.com.annuum.capsicum.api.domain.Availability;
import br.com.annuum.capsicum.api.domain.DayShiftAvailability;
import br.com.annuum.capsicum.api.domain.enums.DayShift;
import one.util.streamex.EntryStream;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map.Entry;

import static java.util.Arrays.asList;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
public class AvailabilityConverter implements AttributeConverter<Availability, Integer> {

    private static final char AVAILABLE = '1';
    private static final char NOT_AVAILABLE = '0';

    @Override
    public Integer convertToDatabaseColumn(Availability attribute) {
        // creates "000000000000000000000" considering 7 days of week and 3 day shifts
        final String emptyValue = Character.toString(NOT_AVAILABLE).repeat(DayOfWeek.values().length * DayShift.values().length);

        // return zero if availability not provided
        if (isNull(attribute) || isEmpty(attribute.getDayShiftAvailabilities())) {
            return 0;
        }

        final StringBuilder builder = new StringBuilder(emptyValue);

        // replace zero to one when availability occurs
        attribute.getDayShiftAvailabilities()
            .forEach(availability ->
                builder.setCharAt(calculateIndex(availability), AVAILABLE));

        return Integer.parseInt(builder.toString(), 2);
    }

    @Override
    public Availability convertToEntityAttribute(Integer dbData) {
        final String data = isNull(dbData) ? EMPTY : StringUtils.leftPad(Integer.toBinaryString(dbData), 21, '0');
        final List<DayShiftAvailability> values = EntryStream.of(asList(data.split(EMPTY)))
            .filterValues(this::isAvailable)
            .map(this::toDayShiftAvailability)
            .toList();

        return new Availability()
            .setDayShiftAvailabilities(values);
    }

    private int calculateIndex(final DayShiftAvailability availability) {
        int dayOfWeek = availability.getDayOfWeek().getValue() - 1;
        int dayShift = availability.getDayShift().getValue() - 1;
        return dayOfWeek * DayShift.values().length + dayShift;
    }

    private boolean isAvailable(final String value) {
        return Character.toString(AVAILABLE).equals(value);
    }

    private DayShiftAvailability toDayShiftAvailability(Entry<Integer, String> integerStringEntry) {
        int index = integerStringEntry.getKey();
        int dayOfWeekValue = index / DayShift.values().length + 1;
        int dayShiftValue = index % DayShift.values().length + 1;

        return new DayShiftAvailability()
            .setDayOfWeek(DayOfWeek.of(dayOfWeekValue))
            .setDayShift(DayShift.of(dayShiftValue));
    }
}
