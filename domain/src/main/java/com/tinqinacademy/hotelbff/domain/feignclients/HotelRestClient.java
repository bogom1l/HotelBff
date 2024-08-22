package com.tinqinacademy.hotelbff.domain.feignclients;

import com.tinqinacademy.hotelbff.domain.feignclients.config.FeignConfig;
import com.tinqinacademy.restexport.HotelRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "hotel", url = "${feign.client.config.hotel.url}", configuration = FeignConfig.class)
public interface HotelRestClient extends HotelRestExportClient {
}
