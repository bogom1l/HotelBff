package com.tinqinacademy.hotelbff.domain;

import com.tinqinacademy.restexport.CommentsRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

//, configuration = FeignConfig.class)
@FeignClient(name = "comments", url = "http://localhost:8082")
public interface CommentsRestClient extends CommentsRestExportClient {
}
