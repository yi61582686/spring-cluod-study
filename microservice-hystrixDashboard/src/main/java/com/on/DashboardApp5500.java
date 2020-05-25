package com.on;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard // 启动HystrixDashboard监控
// 访问 ip:port/hystrix即可。输入对应项目的ip:port/actuator/hystrix.stream地址就可以访问监控页面，查看最近10秒的数据。
public class DashboardApp5500 {
    
    public static void main(String[] args) {
        SpringApplication.run(DashboardApp5500.class);
    }
    
}
