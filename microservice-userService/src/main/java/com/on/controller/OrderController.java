package com.on.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.on.feign.OrderFeign;
import com.on.util.ResponseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;


@RestController
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @Resource
    OrderFeign orderFeign;

    private static final String ORDER_URL = "http://ORDER-SERVICE";

    // RestTemplate远程调用
    @RequestMapping("/getOrderByUser")
    public ResponseObject getOrder() {
        return ResponseObject.success("user-order",
                restTemplate.getForObject("http://localhost:5000/getOrder", ResponseObject.class));
    }

    // RestTemplate的负载均衡
    @RequestMapping("/getOrderByUserUseLoadBalance")
    // 增加Hystrix断路器，提供回退方法与限流。RestTemplate默认支持Hystrix。提供jar包即可，不需要单独配置。
    @HystrixCommand(fallbackMethod = "getOrderByFeignFallBack",  // 开启Hystrix，提供回退方法
            /**
             * 限流配置。
             * threadPoolKey：引用配置文件中名为order的线程池配置。
             * threadPoolProperties：在注解中配置线程池对应的属性值，与threadPoolKey二者不同时使用。
             *  coreSize属性：同时只能接受这么请求。超过线程数后的请求会被拒绝，去执行fallback方法。
            */
            threadPoolKey = "order",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "5")}
    ) // 开启Hystrix，提供回退方法。
    public ResponseObject getOrderLoadBalance() {
        return ResponseObject.success("user-order-loadBalance",
                restTemplate.getForObject(ORDER_URL + "/getOrder", ResponseObject.class));
    }

    // 使用Feign
    @RequestMapping("/userGetOrderByFeign")
    @HystrixCommand(//threadPoolKey = "order",
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "5")})
    public ResponseObject getOrderByFeign() {
        System.out.println("调用真实方法");
        return ResponseObject.success("user-order-feign", orderFeign.order());
    }

    // Hystrix 回退方法
    public ResponseObject getOrderByFeignFallBack() {
        System.out.println("调用回退方法");
        return ResponseObject.error("user-order-feign", "hystrix-fallback");
    }
    
}
