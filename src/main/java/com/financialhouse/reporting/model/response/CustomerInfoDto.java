package com.financialhouse.reporting.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class CustomerInfoDto {

    private Long id;

    private String number;

    private String email;

    @JsonProperty(value = "billing_first_name")
    private String billingFirstName;

    @JsonProperty(value = "billing_last_name")
    private String billingLastName;

    @JsonProperty(value = "created_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date createdDate;

    @JsonProperty(value = "updated_at")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBillingFirstName() {
        return billingFirstName;
    }

    public void setBillingFirstName(String billingFirstName) {
        this.billingFirstName = billingFirstName;
    }

    public String getBillingLastName() {
        return billingLastName;
    }

    public void setBillingLastName(String billingLastName) {
        this.billingLastName = billingLastName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}