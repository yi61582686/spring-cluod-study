package com.on;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AppEureka3000 { // 端口号为3000，单机的eureka。集群使用3001-3003。

    public static void main(String[] args) {
        SpringApplication.run(AppEureka3000.class);
    }

}
