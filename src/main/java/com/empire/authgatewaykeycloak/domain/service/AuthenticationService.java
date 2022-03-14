package com.empire.authgatewaykeycloak.domain.service;

import com.empire.authgatewaykeycloak.api.rest.model.response.AuthenticationResponseModel;
import com.empire.authgatewaykeycloak.domain.dto.AuthenticationDTO;
import com.empire.authgatewaykeycloak.domain.repository.KeyCloakRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private KeyCloakRepository keyCloakRepository;

    public AuthenticationService(KeyCloakRepository keyCloakRepository) {
        this.keyCloakRepository = keyCloakRepository;
    }

    public AuthenticationResponseModel authenticate(String username, String password) {
        AuthenticationDTO authenticationDTO = keyCloakRepository.getAuthentication(username, password);
        return new AuthenticationResponseModel(authenticationDTO);
    }

}
