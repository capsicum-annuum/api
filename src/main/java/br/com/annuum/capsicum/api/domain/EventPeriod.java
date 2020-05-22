package br.com.annuum.capsicum.api.domain;

import br.com.annuum.capsicum.api.annotation.DateStartBeforeDateEnd;

import java.time.LocalDateTime;

@DateStartBeforeDateEnd
public interface EventPeriod {

    LocalDateTime getDateTimeStart();

    LocalDateTime getDateTimeEnd();
}
