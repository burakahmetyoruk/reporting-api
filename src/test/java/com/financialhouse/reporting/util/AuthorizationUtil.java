package com.financialhouse.reporting.util;

import com.financialhouse.reporting.model.request.LoginRequest;
import com.financialhouse.reporting.model.response.LoginResponse;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

public class AuthorizationUtil {

    public static String getAuthorizationToken(TestRestTemplate testRestTemplate, int port) throws URISyntaxException {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@email.com");
        loginRequest.setPassword("pass");

        HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest, new HttpHeaders());
        ResponseEntity<LoginResponse> response = testRestTemplate.exchange(
                URICreator.createURLWithPort("/api/v3/merchant/user/login", port), HttpMethod.POST, entity, LoginResponse.class);
        return response.getBody().getToken();
    }
}
