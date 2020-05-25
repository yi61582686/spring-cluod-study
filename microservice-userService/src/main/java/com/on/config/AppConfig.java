package com.on.config;

import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesCommandDefault;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import feign.Retryer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // 修改server.port的一种方式，不用在配置文件中指定，但是在eureka中获取不到这个值，获取到的是默认值8080
//    @Bean
//    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
//        TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
//        tomcatFactory.setPort(4000);
//        return tomcatFactory;
//    }

    // 使用RestTemplate负载均衡
    @Bean
    @LoadBalanced // 为RestTemplate添加Ribbon负载均衡，不添加该注解时，不会实现负载均衡
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }

    // 修改Ribbon负载均衡策略，默认是轮询
    @Bean
    public IRule iRule() {
        return new RandomRule(); // 改为随机策略
    }

}
