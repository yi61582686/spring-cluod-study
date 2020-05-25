package com.on;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppEureka3002 { // 端口号为3002，集群使用3001-3003。单机的eureka使用3000。

    public static void main(String[] args) {
        SpringApplication.run(AppEureka3002.class);
    }

}
