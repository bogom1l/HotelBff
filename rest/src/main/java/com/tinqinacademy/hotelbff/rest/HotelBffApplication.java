package com.tinqinacademy.hotelbff.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = "com.tinqinacademy.hotelbff")
@EnableFeignClients(basePackages = "com.tinqinacademy.hotelbff.domain.feignclients")
public class HotelBffApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBffApplication.class, args);
    }

}
