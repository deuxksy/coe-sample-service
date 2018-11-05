package com.sds.act.coe.order.service;

import com.sds.act.coe.order.api.CustomerClient;
import com.sds.act.coe.order.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private CustomerClient customerClient;

    @Autowired
    public OrderService(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    public List<Customer> getAllCustomer() {
        return customerClient.findAll();
    }

}
