package com.financialhouse.reporting.model.converter;

import com.financialhouse.reporting.entity.CustomerInfo;
import com.financialhouse.reporting.model.response.CustomerInfoDto;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CustomerInfoConverterTest {

    private CustomerInfoConverter customerInfoConverter;

    @Before
    public void setUp() {
        customerInfoConverter = new CustomerInfoConverter();
    }

    @Test
    public void should_convert_customer_info_to_customer_info_dto() {
        //given
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setId(1L);
        customerInfo.setBillingFirstName("first-name");
        customerInfo.setBillingLastName("last-name");
        customerInfo.setEmail("email@email.com");
        customerInfo.setCreatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setUpdatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setNumber(UUID.randomUUID().toString());

        //when
        final CustomerInfoDto customerInfoDto = customerInfoConverter.apply(customerInfo);

        //then
        assertNotNull(customerInfoDto);
        assertEquals(customerInfo.getBillingFirstName(), customerInfoDto.getBillingFirstName());
        assertEquals(customerInfo.getBillingLastName(), customerInfoDto.getBillingLastName());
        assertEquals(customerInfo.getNumber(), customerInfoDto.getNumber());
        assertEquals(customerInfo.getCreatedDate(), customerInfoDto.getCreatedDate());
        assertEquals(customerInfo.getUpdatedDate(), customerInfoDto.getUpdatedDate());
        assertEquals(customerInfo.getEmail(), customerInfoDto.getEmail());
        assertEquals(customerInfo.getId(), customerInfoDto.getId());
    }
}