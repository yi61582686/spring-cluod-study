package com.on.controller;

import com.on.util.ResponseObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @RequestMapping("/getOrder")
    public ResponseObject getOrder() {
        return ResponseObject.success("order1");
    }

}
