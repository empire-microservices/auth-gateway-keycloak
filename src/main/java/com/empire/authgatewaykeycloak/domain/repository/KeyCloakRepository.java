package com.empire.authgatewaykeycloak.domain.repository;

import com.empire.authgatewaykeycloak.domain.dto.AuthenticationDTO;
import com.empire.authgatewaykeycloak.domain.exceptions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.empire.authgatewaykeycloak.domain.enumeration.ExceptionMessagesEnum.INTEGRATION_EXCEPTION;
import static com.empire.authgatewaykeycloak.domain.exceptions.NonRetryableIntegrationException.isNonRetryableErrorStatusCode;

@Repository
public class KeyCloakRepository {

    @Value("${api.keycloak.authenticate.url}")
    private String authenticateUrl;

    @Value("${api.keycloak.clientId}")
    private String clientId;

    @Value("${api.keycloak.clientSecret}")
    private String clientSecret;

    @Value("${api.keycloak.clientScope}")
    private String clientScope;

    @Retryable(maxAttempts = 10, value = RetryableIntegrationException.class, backoff = @Backoff(delay = 1000))
    public AuthenticationDTO getAuthentication(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity<Object> response;

        try {
            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("username", username);
            map.add("password", password);
            map.add("client_id", this.clientId);
            map.add("client_secret", this.clientSecret);
            map.add("scope", this.clientScope);
            map.add("grant_type", "password");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
            response = restTemplate.exchange(authenticateUrl, HttpMethod.POST, request, Object.class);
        } catch (HttpClientErrorException e) {
            throw new ApiException(e.getStatusCode(), e.getMessage());
        } catch (Exception e) {
            throw new RetryableIntegrationException(INTEGRATION_EXCEPTION, HttpMethod.POST, HttpStatus.INTERNAL_SERVER_ERROR, authenticateUrl, e.getMessage());
        }
        HttpStatus responseCode = response.getStatusCode();
        if (Objects.equals(HttpStatus.OK, responseCode)) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.convertValue(response.getBody(), AuthenticationDTO.class);
        } else {
            if (isNonRetryableErrorStatusCode(responseCode))
                throw new NonRetryableIntegrationException(INTEGRATION_EXCEPTION, HttpMethod.POST, responseCode, authenticateUrl, response.getBody().toString());
        }
        throw new RetryableIntegrationException(INTEGRATION_EXCEPTION, HttpMethod.POST, responseCode, authenticateUrl, response.getBody().toString());
    }
}
