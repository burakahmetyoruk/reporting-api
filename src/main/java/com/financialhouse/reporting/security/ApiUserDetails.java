package com.financialhouse.reporting.security;

import com.financialhouse.reporting.entity.ApiUser;
import com.financialhouse.reporting.model.Role;
import com.financialhouse.reporting.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserDetails implements UserDetailsService {

    private final UserRepository userRepository;

    public ApiUserDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<ApiUser> apiUserOptional = userRepository.findByEmail(username);
        apiUserOptional.orElseThrow(() -> new UsernameNotFoundException("ApiUser '" + username + "' not found"));

        ApiUser apiUser = apiUserOptional.get();
        return User
                .withUsername(username)
                .password(apiUser.getPassword())
                .authorities(Role.API_USER)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
