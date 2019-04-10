package com.financialhouse.reporting.repository;

import com.financialhouse.reporting.entity.CustomerInfo;
import org.springframework.data.repository.CrudRepository;

public interface CustomerInfoRepository extends CrudRepository<CustomerInfo, Long> {
}
