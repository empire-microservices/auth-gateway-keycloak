package com.empire.authgatewaykeycloak.domain.enumeration;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public enum ExceptionMessagesEnum {

    //304
    NOT_MODIFIED_DEFAULT(304001, "The modifier is the same of object", HttpStatus.NOT_MODIFIED),

    //404
    EXAMPLE_NOT_FOUND(404001, "Example not found for Id informed", HttpStatus.NOT_FOUND),

    //500
    INTEGRATION_EXCEPTION(500002, "Unexpected response from external server occurred. httpMethod=%s | httpStatusCode=%s | uri=%s | responseBody=%s", INTERNAL_SERVER_ERROR);

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    ExceptionMessagesEnum(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
