package com.tinqinacademy.hotelbff.domain;


import com.tinqinacademy.restexport.HotelRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

//todo @Headers("Content-Type: application/json")
@FeignClient(name = "hotel", url = "http://localhost:8080", configuration = FeignConfig.class)
public interface HotelRestClient extends HotelRestExportClient {
}
