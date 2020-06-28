package br.com.annuum.capsicum.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StatusTransitionExcepetion extends RuntimeException {

    public StatusTransitionExcepetion(final String description) {
        super(description);
    }
}
