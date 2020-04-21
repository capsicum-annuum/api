package br.com.annuum.capsicum.api.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {

    @SuppressWarnings("UseOfObsoleteDateTimeApi")
    public static final Date toDate(final LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
