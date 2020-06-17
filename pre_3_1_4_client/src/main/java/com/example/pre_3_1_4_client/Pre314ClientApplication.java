package com.example.pre_3_1_4_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Pre314ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(Pre314ClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("admin", "1"));
        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("user", "1"));
        return restTemplate;
    }

}
