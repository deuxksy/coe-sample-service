package com.sds.act.coe.order.web.rest;

import com.sds.act.coe.order.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.sds.act.coe.order.web.rest.ApiConstants.API_V1_BASE_PATH;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    private static final String URL = "http://localhost";

    @LocalServerPort
    private String PORT;

    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders initHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        return httpHeaders;
    }

    @Test
    public void GET_getAllCustomer() {
        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(URL + ":" + PORT + API_V1_BASE_PATH + "/customers"
                , HttpMethod.GET
                , new HttpEntity(null, initHeader())
                , new ParameterizedTypeReference<List<Customer>>() {
                });
        List<Customer> body = responseEntity.getBody();
        assertThat(body.size()).isEqualTo(7);
    }


    @Test
    public void POST_registerCustomer() {
        Customer customer = new Customer();
        customer.setName("Act");
        customer.setEmail("act@act.com");

        ResponseEntity<Customer> responseEntity = restTemplate.exchange(URL + ":" + PORT + API_V1_BASE_PATH + "/customers"
                , HttpMethod.POST
                , new HttpEntity<>(customer, initHeader())
                , Customer.class);

        Customer responseCustomer = responseEntity.getBody();
        assertThat(responseCustomer).hasFieldOrPropertyWithValue("name", "Act");
        assertThat(responseCustomer).hasFieldOrPropertyWithValue("email", "act@act.com");
    }
}