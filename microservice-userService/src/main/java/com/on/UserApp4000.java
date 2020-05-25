package com.on;

import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import ribbon.rule.OrderServiceRule;
import ribbon.rule.PaymentRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@EnableEurekaClient
// 为不同服务配置不同负载均衡策略，策略配置类不能被Spring扫描到，需要放在外面的包中，
// 这里放在了ribbon.rule包中。ribbon与com同级。
@RibbonClients({
        @RibbonClient(name = "ORDER-SERVICE", configuration = OrderServiceRule.class),
        @RibbonClient(name = "PAYMENT-SERVICE", configuration = PaymentRule.class),
})
@EnableFeignClients // 使用Feign
@EnableHystrix // 使用Hystrix断路器
public class UserApp4000 {

    public static void main(String[] args) {
        SpringApplication.run(UserApp4000.class);
    }

}
