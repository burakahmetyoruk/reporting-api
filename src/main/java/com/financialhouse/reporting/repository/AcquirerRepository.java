package com.financialhouse.reporting.repository;

import com.financialhouse.reporting.entity.Acquirer;
import com.financialhouse.reporting.entity.CustomerInfo;
import org.springframework.data.repository.CrudRepository;

public interface AcquirerRepository extends CrudRepository<Acquirer, Long> {
}
