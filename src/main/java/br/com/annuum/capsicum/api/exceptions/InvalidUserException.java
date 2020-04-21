package br.com.annuum.capsicum.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidUserException extends UsernameNotFoundException {

    public InvalidUserException() {
        super("Unkown user");
    }

}
