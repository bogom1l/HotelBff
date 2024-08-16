package com.tinqinacademy.hotelbff.domain.feignclients;

import com.tinqinacademy.hotelbff.domain.feignclients.config.FeignConfig;
import com.tinqinacademy.restexport.HotelRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "hotel", url = "http://localhost:8080", configuration = FeignConfig.class)
public interface HotelRestClient extends HotelRestExportClient {
}
