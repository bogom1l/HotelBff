package com.tinqinacademy.hotelbff.domain.feignclients;

import com.tinqinacademy.authentication.restexport.AuthRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "authentication", url = "${feign.client.config.authentication.url}")
public interface AuthRestClient extends AuthRestExportClient {
}
