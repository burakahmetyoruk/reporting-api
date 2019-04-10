package com.financialhouse.reporting.repository;

import com.financialhouse.reporting.entity.ApiUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<ApiUser, Long> {

    boolean existsByEmail(String email);

    Optional<ApiUser> findByEmail(String email);
}