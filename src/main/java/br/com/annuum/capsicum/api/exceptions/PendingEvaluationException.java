package br.com.annuum.capsicum.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PendingEvaluationException extends RuntimeException {

    public PendingEvaluationException(final String description) {
        super(description);
    }
}
