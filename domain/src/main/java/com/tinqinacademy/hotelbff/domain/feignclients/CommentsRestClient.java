package com.tinqinacademy.hotelbff.domain.feignclients;

import com.tinqinacademy.hotelbff.domain.feignclients.config.FeignConfig;
import com.tinqinacademy.restexport.CommentsRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "comments", url = "http://localhost:8082", configuration = FeignConfig.class)
public interface CommentsRestClient extends CommentsRestExportClient {
}
