package com.empire.authgatewaykeycloak.domain.exceptions;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class ApiException extends HttpException {

    private static final long serialVersionUID = 1L;

    public ApiException(final String message) {
        super(message, INTERNAL_SERVER_ERROR);
    }

    public ApiException(final HttpStatus code, String message) {
        super(code.value(), message, code);
    }

}
