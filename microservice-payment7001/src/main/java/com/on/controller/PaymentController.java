package com.on.controller;

import com.on.util.ResponseObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @RequestMapping("/pay")
    public ResponseObject pay() {
        return ResponseObject.success("pay1");
    }

}
