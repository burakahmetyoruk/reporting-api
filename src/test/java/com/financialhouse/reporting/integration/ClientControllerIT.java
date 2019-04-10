package com.financialhouse.reporting.integration;

import com.financialhouse.reporting.model.response.CustomerInfoDto;
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

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_retrieve_customer_info() throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + AuthorizationUtil.getAuthorizationToken(restTemplate, port));
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        ResponseEntity<CustomerInfoDto> response = restTemplate.exchange(URICreator.createURLWithPort("/api/v3/client/" + "b1f3efe3-0636-4360-9ef3-b89ad463dad7", port), HttpMethod.POST, entity, CustomerInfoDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        CustomerInfoDto customerInfoDto = response.getBody();
        assertNotNull(customerInfoDto);
        assertEquals("1XXX345", customerInfoDto.getNumber());
        assertEquals("customer@email.com", customerInfoDto.getEmail());
        assertEquals("first-name", customerInfoDto.getBillingFirstName());
        assertEquals("last-name", customerInfoDto.getBillingLastName());
    }
}