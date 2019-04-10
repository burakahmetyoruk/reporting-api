package com.financialhouse.reporting.validator;

import com.financialhouse.reporting.exception.InvalidRequestException;
import com.financialhouse.reporting.model.request.LoginRequest;
import org.junit.Test;

public class LoginRequestValidatorTest {

    @Test
    public void should_validate() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password");
        loginRequest.setEmail("test@email.com");

        LoginRequestValidator.validate(loginRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_throw_invalid_request_exception_when_email_not_present() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("password");

        LoginRequestValidator.validate(loginRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_throw_invalid_request_exception_when_password_not_present() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@email.com");

        LoginRequestValidator.validate(loginRequest);
    }
}