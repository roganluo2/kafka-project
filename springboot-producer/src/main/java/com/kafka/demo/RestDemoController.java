package com.kafka.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestDemoController {


    @GetMapping("/index")
    public String index ()
    {
        return "hello world";
    }

}
