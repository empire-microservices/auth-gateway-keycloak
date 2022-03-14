package com.empire.authgatewaykeycloak.api.rest.model.response;

import com.empire.authgatewaykeycloak.domain.dto.AuthenticationDTO;

public class AuthenticationResponseModel {

    private String accessToken;

    private String refreshToken;

    private String accessTokenExpiresIn;

    private String refreshTokenExpiresIn;

    private String tokenType;

    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(String accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public String getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(String refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public AuthenticationResponseModel() {
    }

    public AuthenticationResponseModel(AuthenticationDTO authenticationDTO) {
        this.accessToken = authenticationDTO.getAccessToken();
        this.refreshToken = authenticationDTO.getRefreshToken();
        this.accessTokenExpiresIn = authenticationDTO.getAccessTokenExpiresIn();
        this.refreshTokenExpiresIn = authenticationDTO.getRefreshTokenExpiresIn();
        this.tokenType = authenticationDTO.getTokenType();
        this.scope = authenticationDTO.getScope();
    }
}
