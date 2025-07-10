package com.api.biblioteca_escolar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AutorComLivrosException extends RuntimeException {
    public AutorComLivrosException(String msg) {
        super(msg);
    }
}
