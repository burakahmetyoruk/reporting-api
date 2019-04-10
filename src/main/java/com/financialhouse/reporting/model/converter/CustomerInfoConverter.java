package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.CustomerInfo;
import com.financialhouse.reporting.model.response.CustomerInfoDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CustomerInfoConverter implements Function<CustomerInfo, CustomerInfoDto> {

    @Override
    public CustomerInfoDto apply(CustomerInfo customerInfo) {
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setBillingFirstName(customerInfo.getBillingFirstName());
        customerInfoDto.setBillingLastName(customerInfo.getBillingLastName());
        customerInfoDto.setCreatedDate(customerInfo.getCreatedDate());
        customerInfoDto.setUpdatedDate(customerInfo.getUpdatedDate());
        customerInfoDto.setEmail(customerInfo.getEmail());
        customerInfoDto.setId(customerInfo.getId());
        customerInfoDto.setNumber(customerInfo.getNumber());

        return customerInfoDto;
    }
}
