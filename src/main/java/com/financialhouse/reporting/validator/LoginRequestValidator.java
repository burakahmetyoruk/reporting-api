package com.financialhouse.reporting.validator;

import com.financialhouse.reporting.exception.InvalidRequestException;
import com.financialhouse.reporting.model.request.LoginRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class LoginRequestValidator {

    public static void validate(LoginRequest loginRequest) {
        if (validateEmail(loginRequest.getEmail()) ||
                validatePassword(loginRequest.getPassword())) {
            throw new InvalidRequestException("Username or Password must not be empty");
        }
    }

    private static boolean validateEmail(String email) {
        return StringUtils.isEmpty(email);
    }

    private static boolean validatePassword(String password) {
        return StringUtils.isEmpty(password);
    }
}