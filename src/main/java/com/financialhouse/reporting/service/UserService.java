package com.financialhouse.reporting.service;

import com.financialhouse.reporting.exception.InvalidAuthenticationException;
import com.financialhouse.reporting.entity.ApiUser;
import com.financialhouse.reporting.model.Role;
import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.request.LoginRequest;
import com.financialhouse.reporting.model.request.SignUpRequest;
import com.financialhouse.reporting.model.response.LoginResponse;
import com.financialhouse.reporting.repository.UserRepository;
import com.financialhouse.reporting.security.JwtTokenProvider;
import com.financialhouse.reporting.validator.LoginRequestValidator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    public LoginResponse login(final LoginRequest loginRequest) {
        try {
            LoginRequestValidator.validate(loginRequest);
            final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            final String token = jwtTokenProvider.createToken(loginRequest.getEmail(), Collections.singletonList(Role.API_USER));
            return new LoginResponse(token, Status.APPROVED);
        } catch (AuthenticationException e) {
            throw new InvalidAuthenticationException("Invalid email or password");
        }
    }

    public String signUp(SignUpRequest signUpRequest) {
        if (!userRepository.existsByEmail(signUpRequest.getUsername())) {
            ApiUser apiUser = new ApiUser();
            apiUser.setEmail(signUpRequest.getUsername());
            apiUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            userRepository.save(apiUser);
            return "";//jwtTokenProvider.createToken(apiUser.getEmail(), apiUser.getRoles());
        } else {
            throw new InvalidAuthenticationException("email is already in use");
        }
    }
}