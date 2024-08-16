package com.tinqinacademy.hotelbff.domain.feignclients;

import com.tinqinacademy.authentication.restexport.AuthRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "authentication", url = "http://localhost:8083")
public interface AuthRestClient extends AuthRestExportClient {
}
