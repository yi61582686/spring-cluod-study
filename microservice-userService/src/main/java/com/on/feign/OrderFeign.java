package com.on.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

// fallback:feign与hystrix整合后回调方法所在的类，需要实现当前feign接口
@FeignClient(name = "ORDER-SERVICE", fallback = OrderFeignFallBack.class) // value:需要远程调用的服务.fallback:回到方法的类
public interface OrderFeign {

    @RequestMapping("/getOrder") // 远程调用的Order服务中对应Controller接口上的地址
    Object order(); // 返回值，Spring会进行装配，可以用String接到.
    
}