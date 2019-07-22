package com.txc.consumer.demo.feign;

import com.txc.consumer.demo.config.FullLogConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value="produce-demo",configuration = FullLogConfiguration.class)
public interface ProducerFeign {

    @PostMapping("test")
    String test();

}
