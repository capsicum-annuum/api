package br.com.annuum.capsicum.api.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyInUseException extends RuntimeException {

    private static final long serialVersionUID = -3594595994875726383L;

    public AlreadyInUseException() {
        super("Resource already in use");
    }

}
