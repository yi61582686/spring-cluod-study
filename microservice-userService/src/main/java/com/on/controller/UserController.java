package com.on.controller;

import com.on.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @RequestMapping("/getUser")
    public ResponseObject getUser() {
        return ResponseObject.success("user");
    }



}
