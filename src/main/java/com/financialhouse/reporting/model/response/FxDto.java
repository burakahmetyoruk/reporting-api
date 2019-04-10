package com.financialhouse.reporting.model.response;

import com.financialhouse.reporting.model.enumaration.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public class FxDto {

    private BigDecimal originalAmount;

    private Currency originalCurrency;

    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(BigDecimal originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Currency getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(Currency originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FxDto)) return false;
        FxDto fxDto = (FxDto) o;
        return Objects.equals(originalAmount, fxDto.originalAmount) &&
                originalCurrency == fxDto.originalCurrency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalAmount, originalCurrency);
    }
}