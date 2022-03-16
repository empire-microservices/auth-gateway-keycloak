package com.empire.authgatewaykeycloak.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Configuration
class RestTemplateConfig{

    private RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Bean
    public RestTemplate restTemplate(RequestInterceptor requestInterceptor) {
        return restTemplateBuilder.interceptors(Collections.singletonList(requestInterceptor)).build();
    }

}