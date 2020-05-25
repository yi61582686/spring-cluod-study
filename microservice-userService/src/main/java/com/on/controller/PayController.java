package com.on.controller;

import com.on.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PayController {

    @Autowired
    RestTemplate restTemplate;

    private static final String PAYMENT_URL = "http://PAYMENT-SERVICE";

    @RequestMapping("/userPay")
    public ResponseObject userPay() {
        return ResponseObject.success("user-pay",
                restTemplate.getForObject(PAYMENT_URL + "/pay", ResponseObject.class));
    }

}
