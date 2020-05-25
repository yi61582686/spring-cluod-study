package com.on.feign;

import com.on.util.ResponseObject;
import org.springframework.stereotype.Component;

@Component
public class OrderFeignFallBack implements OrderFeign {
    
    @Override
    public Object order() {
        return ResponseObject.error("user-order-feign-hystrix", "feign-hystrix-fallback");
    }
    
}
