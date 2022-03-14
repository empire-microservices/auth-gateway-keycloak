package com.empire.authgatewaykeycloak.domain.exceptions;

import com.empire.authgatewaykeycloak.domain.enumeration.ExceptionMessagesEnum;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.springframework.http.HttpStatus.*;

public class NonRetryableIntegrationException extends HttpException {

    private static final long serialVersionUID = 1L;

    public NonRetryableIntegrationException(final ExceptionMessagesEnum exceptionMessagesEnum, HttpMethod httpMethod, HttpStatus httpStatus, String uri, String body) {
        super(exceptionMessagesEnum.getCode(), String.format(exceptionMessagesEnum.getMessage(), httpMethod.toString(), httpStatus.toString(), uri, body), INTERNAL_SERVER_ERROR);
    }

    public static boolean isNonRetryableErrorStatusCode(HttpStatus httpStatus) {
        return Arrays.asList(BAD_REQUEST,
                UNAUTHORIZED,
                FORBIDDEN,
                METHOD_NOT_ALLOWED,
                PAYLOAD_TOO_LARGE,
                UNSUPPORTED_MEDIA_TYPE,
                REQUEST_HEADER_FIELDS_TOO_LARGE,
                NOT_IMPLEMENTED,
                HTTP_VERSION_NOT_SUPPORTED).contains(httpStatus);
    }

}
