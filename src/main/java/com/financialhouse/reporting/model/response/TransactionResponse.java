package com.financialhouse.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TransactionResponse {

    @JsonProperty(value = "customer_info")
    private CustomerInfoDto customerInfoDto;

    @JsonProperty(value = "fx")
    private FxDto fxDto;

    @JsonProperty(value = "merchant")
    private MerchantDto merchantDto;

    @JsonProperty(value = "acquirer")
    private AcquirerDto acquirerDto;

    @JsonProperty(value = "reference_no")
    private String referenceNo;

    @JsonProperty(value = "merchant_transactions")
    private List<MerchantTransaction> merchantTransactionList;

    public CustomerInfoDto getCustomerInfoDto() {
        return customerInfoDto;
    }

    public void setCustomerInfoDto(CustomerInfoDto customerInfoDto) {
        this.customerInfoDto = customerInfoDto;
    }

    public FxDto getFxDto() {
        return fxDto;
    }

    public void setFxDto(FxDto fxDto) {
        this.fxDto = fxDto;
    }

    public MerchantDto getMerchantDto() {
        return merchantDto;
    }

    public void setMerchantDto(MerchantDto merchantDto) {
        this.merchantDto = merchantDto;
    }

    public AcquirerDto getAcquirerDto() {
        return acquirerDto;
    }

    public void setAcquirerDto(AcquirerDto acquirerDto) {
        this.acquirerDto = acquirerDto;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public List<MerchantTransaction> getMerchantTransactionList() {
        return merchantTransactionList;
    }

    public void setMerchantTransactionList(List<MerchantTransaction> merchantTransactionList) {
        this.merchantTransactionList = merchantTransactionList;
    }
}
