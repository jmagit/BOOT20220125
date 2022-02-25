package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableEurekaClient
@SpringBootApplication
public class MegustaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MegustaApplication.class, args);
	}

}
