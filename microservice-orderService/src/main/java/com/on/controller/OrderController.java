package com.on.controller;

import com.on.util.ResponseObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    

    @RequestMapping("/getOrder")
    public ResponseObject getOrder(String id) throws Exception{
        
        if(id == null)
//            throw new Exception(); // 测试 Hystrix 断路器，这里抛出异常，使调用失败

        //Thread.sleep(2 * 1000); // 测试 Hystrix 断路器，这里设置休眠，使调用超时。Hystrix默认超时时间是1s。
        System.out.println("OrderService is called");
        return ResponseObject.success("order");
        
    }

}
