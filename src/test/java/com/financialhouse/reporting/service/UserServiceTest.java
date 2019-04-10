package com.financialhouse.reporting.service;

import com.financialhouse.reporting.exception.InvalidRequestException;
import com.financialhouse.reporting.model.Role;
import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.request.LoginRequest;
import com.financialhouse.reporting.model.response.LoginResponse;
import com.financialhouse.reporting.repository.UserRepository;
import com.financialhouse.reporting.security.JwtTokenProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private AuthenticationManager authenticationManager;

    @Before
    public void setUp() {
        userService = new UserService(userRepository, passwordEncoder, jwtTokenProvider, authenticationManager);
    }

    @Test
    public void should_login_and_retrieve_token() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("email@email.com");
        loginRequest.setPassword("password");

        String token = "eyJhbGciOiJIUzUxMiJ9." +
                "eyJzdWIiOiJ1c2VyIiwiYXV0aCI6W3siYXV0aG9yaXR5IjoiQVBJX1VTRVIifV0sImlhdCI6MTU1NDkwMDE1MiwiZXhwIjoxNTU0OTAwNzUyfQ." +
                "Pit64JKOs3CKTZuW-kzoBE_pr-_L3XpyDDH25TGQJkPBzYIPRX8vsPszrqD8NGpepovXa7EBhE_Ymg4weFNkog";

        when(jwtTokenProvider.createToken(loginRequest.getEmail(), Collections.singletonList(Role.API_USER))).thenReturn(token);
        LoginResponse loginResponse = userService.login(loginRequest);

        assertNotNull(loginResponse);
        assertEquals(Status.APPROVED, loginResponse.getStatus());
        assertEquals(token, loginResponse.getToken());
    }

    @Test(expected = InvalidRequestException.class)
    public void should_throw_invalid_request_exception_when_username_or_password_not_present_in_request() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password");

        userService.login(loginRequest);
    }
}