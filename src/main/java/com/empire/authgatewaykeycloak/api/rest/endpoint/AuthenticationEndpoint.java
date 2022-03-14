package com.empire.authgatewaykeycloak.api.rest.endpoint;

import com.empire.authgatewaykeycloak.api.rest.model.request.AuthenticationRequestModel;
import com.empire.authgatewaykeycloak.api.rest.model.response.AuthenticationResponseModel;
import com.empire.authgatewaykeycloak.domain.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/api")
@RestController
public class AuthenticationEndpoint {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationEndpoint(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(path = "/authenticate")
    private ResponseEntity<AuthenticationResponseModel> authenticate(@RequestBody @Valid AuthenticationRequestModel authenticationRequestModel) {
        String username = authenticationRequestModel.getUsername();
        String password = authenticationRequestModel.getPassword();
        return ResponseEntity.ok().body(authenticationService.authenticate(username, password));
    }
}
