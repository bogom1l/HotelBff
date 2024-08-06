package com.tinqinacademy.hotelbff.domain;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Client;
import feign.okhttp.OkHttpClient;

@Configuration
public class FeignConfig {

    @Bean
    public Client feignClient() {
        return new OkHttpClient();
    }

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return new RequestInterceptor() {
//            @Override
//            public void apply(RequestTemplate template) {
//                if (template.method().equals("PATCH")) {
//                    template.header("Content-Type", "application/json-patch+json");
//                } else {
//                    template.header("Content-Type", "application/json");
//                }
//            }
//        };
//    }

}