package com.on;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy // eureka注解不需要加，zuul会自动和eureka整合。把自己注册到eureka，并从eureka获取到服务的配置信息
public class ZuulApp9000 {
    
    public static void main(String[] args) {
        SpringApplication.run(ZuulApp9000.class);
    }
    
}
