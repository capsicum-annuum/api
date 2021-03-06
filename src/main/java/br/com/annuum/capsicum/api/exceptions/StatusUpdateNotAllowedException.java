package br.com.annuum.capsicum.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusUpdateNotAllowedException extends RuntimeException {

    public StatusUpdateNotAllowedException(final String description) {
        super(description);
    }
}
