package com.on;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PaymentApp7000 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentApp7000.class);
    }

}
