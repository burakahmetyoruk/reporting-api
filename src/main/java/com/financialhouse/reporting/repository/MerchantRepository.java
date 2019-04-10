package com.financialhouse.reporting.repository;

import com.financialhouse.reporting.entity.Merchant;
import org.springframework.data.repository.CrudRepository;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
}
