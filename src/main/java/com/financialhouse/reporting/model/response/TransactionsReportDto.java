package com.financialhouse.reporting.model.response;

import com.financialhouse.reporting.model.enumaration.Currency;

public class TransactionsReportDto {

    private Integer count;
    private Integer total;
    private Currency currency;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}