package br.com.annuum.capsicum.api.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@SuppressWarnings("UseOfObsoleteDateTimeApi")
class DateUtilsTest {

  @Test
  public void shouldConvertLocalDateTimeIntoDate() {
    final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
    final LocalDateTime dateTime = LocalDateTime.of(2020, 4, 10, 23, 59, 58);

    final Date date = DateUtils.toDate(dateTime);

    Assertions.assertEquals("10/04/2020 11:59:58 PM", dateFormat.format(date));
  }
}