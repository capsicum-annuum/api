package br.com.annuum.capsicum.api.converter;

import static br.com.annuum.capsicum.api.domain.enums.DayShift.AFTERMOON;
import static br.com.annuum.capsicum.api.domain.enums.DayShift.MORNING;
import static br.com.annuum.capsicum.api.domain.enums.DayShift.NIGHT;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.annuum.capsicum.api.domain.Availability;
import br.com.annuum.capsicum.api.domain.DayShiftAvailability;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AvailabilityConverterTest {

  private AvailabilityConverter target = new AvailabilityConverter();

  //  --------------------
  // | TO DATABASE COLUMN |
  //  --------------------

  @Test
  public void shouldConvertToDatabaseColumnWithEmptyAvailabilityWhenNull() {

    final String column = target.convertToDatabaseColumn(null);

    assertEquals("000000000000000000000", column);
  }

  @Test
  public void shouldConvertToDatabaseColumnWithEmptyAvailabilityWhenEmptyDayShiftAvailability() {

    final Availability attribute = new Availability()
        .setDayShiftAvailabilities(new ArrayList<>());

    final String column = target.convertToDatabaseColumn(attribute);

    assertEquals("000000000000000000000", column);
  }

  @Test
  public void shouldConvertToDatabaseColumnWithFirstAndLastDayShiftAvailableIndicatingMondayMorningAndSundayNight() {

    final Availability attribute = new Availability()
        .setDayShiftAvailabilities(asList(
            new DayShiftAvailability()
                .setDayOfWeek(MONDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(SUNDAY)
                .setDayShift(NIGHT)
        ));

    final String column = target.convertToDatabaseColumn(attribute);

    assertEquals("100000000000000000001", column);
  }

  @Test
  public void shouldConvertToDatabaseColumnWithEveryMorningsAvailable() {

    final Availability attribute = new Availability()
        .setDayShiftAvailabilities(asList(
            new DayShiftAvailability()
                .setDayOfWeek(MONDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(TUESDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(WEDNESDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(THURSDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(FRIDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(SATURDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(SUNDAY)
                .setDayShift(MORNING)
        ));

    final String column = target.convertToDatabaseColumn(attribute);

    assertEquals("100100100100100100100", column);
  }

  @Test
  public void shouldConvertToDatabaseColumnWithAvailabilityOnlyOnWeekends() {

    final Availability attribute = new Availability()
        .setDayShiftAvailabilities(asList(
            new DayShiftAvailability()
                .setDayOfWeek(SATURDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(SATURDAY)
                .setDayShift(AFTERMOON),
            new DayShiftAvailability()
                .setDayOfWeek(SATURDAY)
                .setDayShift(NIGHT),
            new DayShiftAvailability()
                .setDayOfWeek(SUNDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(SUNDAY)
                .setDayShift(AFTERMOON),
            new DayShiftAvailability()
                .setDayOfWeek(SUNDAY)
                .setDayShift(NIGHT)
        ));

    final String column = target.convertToDatabaseColumn(attribute);

    assertEquals("000000000000000111111", column);
  }

  @Test
  public void shouldConvertToDatabaseColumnWithAvailabilityOnlyOnWeekDays() {

    final Availability attribute = new Availability()
        .setDayShiftAvailabilities(asList(
            new DayShiftAvailability()
                .setDayOfWeek(MONDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(MONDAY)
                .setDayShift(AFTERMOON),
            new DayShiftAvailability()
                .setDayOfWeek(MONDAY)
                .setDayShift(NIGHT),
            new DayShiftAvailability()
                .setDayOfWeek(TUESDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(TUESDAY)
                .setDayShift(AFTERMOON),
            new DayShiftAvailability()
                .setDayOfWeek(TUESDAY)
                .setDayShift(NIGHT),
            new DayShiftAvailability()
                .setDayOfWeek(WEDNESDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(WEDNESDAY)
                .setDayShift(AFTERMOON),
            new DayShiftAvailability()
                .setDayOfWeek(WEDNESDAY)
                .setDayShift(NIGHT),
            new DayShiftAvailability()
                .setDayOfWeek(THURSDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(THURSDAY)
                .setDayShift(AFTERMOON),
            new DayShiftAvailability()
                .setDayOfWeek(THURSDAY)
                .setDayShift(NIGHT),
            new DayShiftAvailability()
                .setDayOfWeek(FRIDAY)
                .setDayShift(MORNING),
            new DayShiftAvailability()
                .setDayOfWeek(FRIDAY)
                .setDayShift(AFTERMOON),
            new DayShiftAvailability()
                .setDayOfWeek(FRIDAY)
                .setDayShift(NIGHT)
        ));

    final String column = target.convertToDatabaseColumn(attribute);

    assertEquals("111111111111111000000", column);
  }

  //  ---------------------
  // | TO ENTITY ATTRIBUTE |
  //  ---------------------

  @Test
  public void shouldConvertToEntityWithEmptyAvailabilityWhenNull() {

    final Availability availability = target.convertToEntityAttribute(null);

    assertEquals(0, availability.getDayShiftAvailabilities().size());
  }

  @Test
  public void shouldConvertToEntityWithEmptyAvailabilityWhenEmptyDayShiftAvailability() {

    final String column = "000000000000000000000";

    final Availability availability = target.convertToEntityAttribute(column);

    assertEquals(0, availability.getDayShiftAvailabilities().size());
  }

  @Test
  public void shouldConvertToEntityWithFirstAndLastDayShiftAvailableIndicatingMondayMorningAndSundayNight() {

    final String column = "100000000000000000001";

    final Availability availability = target.convertToEntityAttribute(column);

    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(MONDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SUNDAY)
          .setDayShift(NIGHT)));
  }

  @Test
  public void shouldConvertToEntityWithEveryMorningsAvailable() {

    final String column = "100100100100100100100";

    final Availability availability = target.convertToEntityAttribute(column);

    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(MONDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(TUESDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(WEDNESDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(THURSDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(FRIDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SATURDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SUNDAY)
          .setDayShift(MORNING)));
  }

  @Test
  public void shouldConvertToEntityWithAvailabilityOnlyOnWeekends() {

    final String column = "000000000000000111111";

    final Availability availability = target.convertToEntityAttribute(column);

    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SATURDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SATURDAY)
          .setDayShift(AFTERMOON)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SATURDAY)
          .setDayShift(NIGHT)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SUNDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SUNDAY)
          .setDayShift(AFTERMOON)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(SUNDAY)
          .setDayShift(NIGHT)));
  }

  @Test
  public void shouldConvertToEntityWithAvailabilityOnlyOnWeekDays() {

    final String column = "111111111111111000000";

    final Availability availability = target.convertToEntityAttribute(column);

    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(MONDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(MONDAY)
          .setDayShift(AFTERMOON)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(MONDAY)
          .setDayShift(NIGHT)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(TUESDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(TUESDAY)
          .setDayShift(AFTERMOON)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(TUESDAY)
          .setDayShift(NIGHT)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(WEDNESDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(WEDNESDAY)
          .setDayShift(AFTERMOON)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(WEDNESDAY)
          .setDayShift(NIGHT)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(THURSDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(THURSDAY)
          .setDayShift(AFTERMOON)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(THURSDAY)
          .setDayShift(NIGHT)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(FRIDAY)
          .setDayShift(MORNING)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(FRIDAY)
          .setDayShift(AFTERMOON)));
    assertTrue(availability.getDayShiftAvailabilities().contains(
        new DayShiftAvailability()
          .setDayOfWeek(FRIDAY)
          .setDayShift(NIGHT)));
  }

}