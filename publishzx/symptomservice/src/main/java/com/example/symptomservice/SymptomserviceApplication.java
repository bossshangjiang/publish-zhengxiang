package com.example.symptomservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SymptomserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SymptomserviceApplication.class, args);
	}
}
