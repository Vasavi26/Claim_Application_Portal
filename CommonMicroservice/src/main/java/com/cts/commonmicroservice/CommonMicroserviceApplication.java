package com.cts.commonmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class CommonMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonMicroserviceApplication.class, args);
	}

}
