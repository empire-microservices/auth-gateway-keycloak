package com.empire.authgatewaykeycloak.domain.exceptions;

import com.empire.authgatewaykeycloak.domain.enumeration.ExceptionMessagesEnum;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class RetryableIntegrationException extends HttpException {

    private static final long serialVersionUID = 1L;

    public RetryableIntegrationException(final ExceptionMessagesEnum exceptionMessagesEnum, HttpMethod httpMethod, HttpStatus httpStatus, String uri, String body) {
        super(exceptionMessagesEnum.getCode(), String.format(exceptionMessagesEnum.getMessage(), httpMethod.toString(), httpStatus.toString(), uri, body), INTERNAL_SERVER_ERROR);
    }

}
