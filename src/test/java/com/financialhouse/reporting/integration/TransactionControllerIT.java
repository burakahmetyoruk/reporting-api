package com.financialhouse.reporting.integration;

import com.financialhouse.reporting.model.enumaration.Currency;
import com.financialhouse.reporting.model.enumaration.Status;
import com.financialhouse.reporting.model.response.*;
import com.financialhouse.reporting.util.AuthorizationUtil;
import com.financialhouse.reporting.util.URICreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_retrieve_transaction_with_transaction_id() throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AuthorizationUtil.getAuthorizationToken(restTemplate, port));

        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<TransactionResponse> response = restTemplate.exchange(URICreator.createURLWithPort("/api/v3/transaction/" + "b1f3efe3-0636-4360-9ef3-b89ad463dad7", port), HttpMethod.POST, entity, TransactionResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        TransactionResponse transactionResponse = response.getBody();

        assertNotNull(transactionResponse);
        assertEquals("reference-no", transactionResponse.getReferenceNo());

        CustomerInfoDto customerInfoDto = transactionResponse.getCustomerInfoDto();
        FxDto fxDto = transactionResponse.getFxDto();
        AcquirerDto acquirerDto = transactionResponse.getAcquirerDto();
        MerchantDto merchantDto = transactionResponse.getMerchantDto();

        assertNotNull(customerInfoDto);
        assertEquals("1XXX345", customerInfoDto.getNumber());
        assertEquals("customer@email.com", customerInfoDto.getEmail());
        assertEquals("first-name", customerInfoDto.getBillingFirstName());
        assertEquals("last-name", customerInfoDto.getBillingLastName());

        assertNotNull(fxDto);
        assertEquals(BigDecimal.TEN, fxDto.getOriginalAmount().setScale(0, RoundingMode.UNNECESSARY));
        assertEquals(Currency.EUR, fxDto.getOriginalCurrency());

        assertNotNull(acquirerDto);
        assertEquals("acquirer", acquirerDto.getName());
        assertEquals(1, acquirerDto.getCode().intValue());

        assertNotNull(merchantDto);
        assertEquals("merchant", merchantDto.getName());
        assertEquals(1, merchantDto.getId().intValue());


        List<MerchantTransaction> merchantTransactionList = transactionResponse.getMerchantTransactionList();
        assertNotNull(merchantTransactionList);
        assertEquals(1, merchantTransactionList.size());

        MerchantTransaction merchantTransaction = merchantTransactionList.get(0);
        assertEquals("reference-no", merchantTransaction.getReferenceNo());
        assertEquals(Status.APPROVED, merchantTransaction.getStatus());
    }
}