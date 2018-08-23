package com.sds.act.coe.order.web.rest;

import com.sds.act.coe.api.user.User;
import com.sds.act.coe.order.api.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sds.act.coe.order.web.rest.ApiConstants.API_V1_BASE_PATH;

@RestController
@RequestMapping(value = API_V1_BASE_PATH + "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    private UserClient userClient;


    public OrderController(@Autowired UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/customers")
    public List<User> getAll() {
        return userClient.search();
    }

    @GetMapping("/customers/{id}")
    public User getCustomer(@PathVariable(name = "id") int id) {
        logger.info("called getCustomer");
        return userClient.get(id);
    }
}