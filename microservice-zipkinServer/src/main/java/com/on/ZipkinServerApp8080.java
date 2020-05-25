package com.on;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinServerApp8080 {
    
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApp8080.class);
    }
    
}
