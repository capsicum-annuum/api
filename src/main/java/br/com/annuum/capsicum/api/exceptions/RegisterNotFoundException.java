package br.com.annuum.capsicum.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RegisterNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -9008147233245444816L;

    public RegisterNotFoundException(final String description) {
        super(description);
    }
}
