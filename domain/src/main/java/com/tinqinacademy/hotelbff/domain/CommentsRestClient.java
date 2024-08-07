package com.tinqinacademy.hotelbff.domain;

import com.tinqinacademy.restexport.CommentsRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "comments", url = "http://localhost:8082") //, configuration = FeignConfig.class)
public interface CommentsRestClient extends CommentsRestExportClient {
}
