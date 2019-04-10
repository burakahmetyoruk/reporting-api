package com.financialhouse.reporting.entity;

import com.financialhouse.reporting.model.enumaration.Currency;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Fx {

    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
