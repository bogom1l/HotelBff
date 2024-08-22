package com.tinqinacademy.hotelbff.domain.feignclients;

import com.tinqinacademy.hotelbff.domain.feignclients.config.FeignConfig;
import com.tinqinacademy.restexport.CommentsRestExportClient;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "comments", url = "${feign.client.config.comments.url}", configuration = FeignConfig.class)
public interface CommentsRestClient extends CommentsRestExportClient {
}
