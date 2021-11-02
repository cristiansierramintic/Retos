package com.usa.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = {"com.usa.api.model"})
@SpringBootApplication
public class Ciclo3retosApplication {

	public static void main(String[] args) {
		SpringApplication.run(Ciclo3retosApplication.class, args);
	}

}
