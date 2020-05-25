package com.on;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer // 声明为配置中心
@EnableEurekaClient // 将自己注册到eureka中，是配置中心高可用
public class ConfigServerApp5600 {
    
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApp5600.class);
    }
    
}
