package com.tinqinacademy.hotelbff.domain.feignclients;

import com.tinqinacademy.restexport.CommentsRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

//todo for the patch req: , configuration = FeignConfig.class)
@FeignClient(name = "comments", url = "http://localhost:8082")
public interface CommentsRestClient extends CommentsRestExportClient {
}
