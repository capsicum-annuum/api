package br.com.annuum.capsicum.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AccessControlException extends RuntimeException {

    public AccessControlException(final String description) {
        super(description);
    }
}
