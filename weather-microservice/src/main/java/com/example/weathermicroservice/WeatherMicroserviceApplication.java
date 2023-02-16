package com.example.weathermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WeatherMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherMicroserviceApplication.class, args);
    }

}
