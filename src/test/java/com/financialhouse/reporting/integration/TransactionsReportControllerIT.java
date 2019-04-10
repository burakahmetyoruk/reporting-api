package com.financialhouse.reporting.integration;

import com.financialhouse.reporting.model.enumaration.Currency;
import com.financialhouse.reporting.model.request.TransactionsReportRequest;
import com.financialhouse.reporting.model.response.TransactionsReportDto;
import com.financialhouse.reporting.model.response.TransactionsReportResponse;
import com.financialhouse.reporting.util.AuthorizationUtil;
import com.financialhouse.reporting.util.URICreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsReportControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_retrieve_transaction_report_between_date() throws URISyntaxException {
        TransactionsReportRequest transactionsReportRequest = new TransactionsReportRequest();
        transactionsReportRequest.setToDate("2019-04-11");
        transactionsReportRequest.setFromDate("2019-04-09");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AuthorizationUtil.getAuthorizationToken(restTemplate, port));
        HttpEntity<TransactionsReportRequest> entity = new HttpEntity<>(transactionsReportRequest, headers);
        ResponseEntity<TransactionsReportResponse> response = restTemplate.exchange(
                URICreator.createURLWithPort("/api/v3/transactions/report", port), HttpMethod.POST, entity, TransactionsReportResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        TransactionsReportResponse transactionsReportResponse = response.getBody();
        assertNotNull(transactionsReportResponse);

        List<TransactionsReportDto> transactionsReportDtoList = transactionsReportResponse.getTransactionsReportDtoList();
        assertNotNull(transactionsReportDtoList);
        assertEquals(1, transactionsReportDtoList.size());

        TransactionsReportDto transactionsReportDto = transactionsReportDtoList.get(0);
        assertEquals(2, transactionsReportDto.getCount().intValue());
        assertEquals(20, transactionsReportDto.getTotal().intValue());
        assertEquals(Currency.EUR, transactionsReportDto.getCurrency());
    }
}