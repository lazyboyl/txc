package com.txc.consumer.demo.controller;

import com.txc.consumer.demo.feign.ProducerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerTest {

    @Autowired
    private ProducerFeign producerFeign;

    @GetMapping("test")
    public String test(){
        return producerFeign.test();
    }

}
