package com.financialhouse.reporting.service;

import com.financialhouse.reporting.entity.CustomerInfo;
import com.financialhouse.reporting.entity.Transaction;
import com.financialhouse.reporting.model.converter.CustomerInfoConverter;
import com.financialhouse.reporting.model.response.CustomerInfoDto;
import com.financialhouse.reporting.repository.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    private ClientService clientService;

    @Mock
    private CustomerInfoConverter customerInfoConverter;

    @Mock
    private TransactionRepository transactionRepository;

    @Before
    public void setUp() {
        clientService = new ClientService(customerInfoConverter, transactionRepository);
    }

    @Test
    public void should_retrieve_customer_info_successfully() {
        //given
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setId(1L);
        customerInfo.setBillingFirstName("first-name");
        customerInfo.setBillingLastName("last-name");
        customerInfo.setEmail("email@email.com");
        customerInfo.setCreatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setUpdatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setNumber(UUID.randomUUID().toString());

        final String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction();
        transaction.setCustomerInfo(customerInfo);
        transaction.setTransactionId(transactionId);

        when(transactionRepository.findByTransactionId(transactionId)).thenReturn(Optional.of(transaction));
        when(customerInfoConverter.apply(customerInfo)).thenReturn(retrieveCustomerInfoResponse(customerInfo));

        //when
        final CustomerInfoDto customerInfoDto = clientService.retrieveCustomerInfo(transactionId);

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

    private CustomerInfoDto retrieveCustomerInfoResponse(CustomerInfo customerInfo) {
        CustomerInfoDto customerInfoDto = new CustomerInfoDto();
        customerInfoDto.setId(customerInfo.getId());
        customerInfoDto.setBillingFirstName(customerInfo.getBillingFirstName());
        customerInfoDto.setBillingLastName(customerInfo.getBillingLastName());
        customerInfoDto.setEmail(customerInfo.getEmail());
        customerInfoDto.setCreatedDate(customerInfo.getCreatedDate());
        customerInfoDto.setUpdatedDate(customerInfo.getUpdatedDate());
        customerInfoDto.setNumber(customerInfo.getNumber());
        return customerInfoDto;
    }
}