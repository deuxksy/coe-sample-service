package com.sds.act.coe.order.web.rest;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OrderControllerTest {

    private static final String URL = "http://localhost";

    @LocalServerPort
    private String PORT;

//    @Autowired
//    private RestTemplate restTemplate;
//
//    private HttpHeaders initHeader() {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-Type", "application/json");
//        return httpHeaders;
//    }
//
//    @Test
//    public void GET_getAllCustomer() {
//        ResponseEntity<List<Customer>> responseEntity = restTemplate.exchange(URL + ":" + PORT + API_V1_BASE_PATH + "/customers"
//                , HttpMethod.GET
//                , new HttpEntity(null, initHeader())
//                , new ParameterizedTypeReference<List<Customer>>() {
//                });
//        List<Customer> body = responseEntity.getBody();
//        assertThat(body.size()).isEqualTo(7);
//    }
//
//
//    @Test
//    public void POST_registerCustomer() {
//        Customer customer = new Customer();
//        customer.setName("Act");
//        customer.setEmail("act@act.com");
//
//        ResponseEntity<Customer> responseEntity = restTemplate.exchange(URL + ":" + PORT + API_V1_BASE_PATH + "/customers"
//                , HttpMethod.POST
//                , new HttpEntity<>(customer, initHeader())
//                , Customer.class);
//
//        Customer responseCustomer = responseEntity.getBody();
//        assertThat(responseCustomer).hasFieldOrPropertyWithValue("name", "Act");
//        assertThat(responseCustomer).hasFieldOrPropertyWithValue("email", "act@act.com");
//    }
}