package com.financialhouse.reporting.integration;

import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.request.LoginRequest;
import com.financialhouse.reporting.model.response.LoginResponse;
import com.financialhouse.reporting.util.URICreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_login_with_email_and_password() throws URISyntaxException {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@email.com");
        loginRequest.setPassword("pass");

        HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest, new HttpHeaders());
        ResponseEntity<LoginResponse> response = restTemplate.exchange(
                URICreator.createURLWithPort("/api/v3/merchant/user/login", port), HttpMethod.POST, entity, LoginResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        LoginResponse loginResponse = response.getBody();
        assertEquals(Status.APPROVED, loginResponse.getStatus());
        assertNotNull(loginResponse.getToken());
    }
}