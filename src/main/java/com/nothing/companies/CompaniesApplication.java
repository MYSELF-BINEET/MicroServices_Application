package com.nothing.companies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CompaniesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompaniesApplication.class, args);
	}

}
