package com.financialhouse.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financialhouse.reporting.model.enumaration.Status;

public class MerchantTransaction {

    @JsonProperty(value = "reference_no")
    private String referenceNo;

    private Status status;

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}