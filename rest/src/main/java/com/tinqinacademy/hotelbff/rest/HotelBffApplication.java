package com.tinqinacademy.hotelbff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.tinqinacademy.hotelbff")
@EntityScan(basePackages = "com.tinqinacademy.authentication.persistence.model")
@EnableJpaRepositories(basePackages = "com.tinqinacademy.hotelbff.persistence.repository")
public class HotelBffApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelBffApplication.class, args);
	}

}
