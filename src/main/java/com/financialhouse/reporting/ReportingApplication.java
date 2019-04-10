package com.financialhouse.reporting;

import com.financialhouse.reporting.entity.*;
import com.financialhouse.reporting.model.enumaration.Currency;
import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.request.SignUpRequest;
import com.financialhouse.reporting.repository.*;
import com.financialhouse.reporting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
public class ReportingApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CustomerInfoRepository customerInfoRepository;

    @Autowired
    private FxRepository fxRepository;

    @Autowired
    private AcquirerRepository acquirerRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    public static void main(String[] args) {
        SpringApplication.run(ReportingApplication.class, args);
    }

    @Override
    public void run(String... params) {
        /*SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUsername("user");
        signUpRequest.setPassword("pass");
        userService.signUp(signUpRequest);

        CustomerInfo customerInfo = new CustomerInfo();
        //customerInfo.setId(1L);
        customerInfo.setBillingFirstName("first-name");
        customerInfo.setBillingLastName("last-name");
        customerInfo.setEmail("email@email.com");
        customerInfo.setCreatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setUpdatedDate(Date.valueOf(LocalDate.now()));
        customerInfo.setNumber(UUID.randomUUID().toString());

        customerInfoRepository.save(customerInfo);

        Fx fx = new Fx();
        fx.setAmount(BigDecimal.TEN);
        fx.setCurrency(Currency.EUR);
        fxRepository.save(fx);

        Acquirer acquirer = new Acquirer();
        acquirer.setName("name");
        acquirer.setId(1L);
        acquirerRepository.save(acquirer);

        Merchant merchant = new Merchant();
        merchant.setId(1L);
        merchant.setName("merchant-name");
        merchantRepository.save(merchant);

        final String transactionId = UUID.randomUUID().toString();
        Transaction transaction = new Transaction();
        transaction.setCustomerInfo(customerInfo);
        transaction.setTransactionId(transactionId);
        transaction.setFx(fx);
        transaction.setAcquirer(acquirer);
        transaction.setMerchant(merchant);
        transaction.setStatus(Status.APPROVED);
        transaction.setReferenceNo("reference-no");

        transactionRepository.save(transaction);*/

    }
}