package com.microservice.kafka_producer.spring_test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {

    private final Service service;

    @GetMapping("getname")
    public String getName() {
        System.out.println("control class name");
        return service.getName();
    }

    public boolean checkBoolean() {
        return true;
    }

    public String throwException(){
        throw new RuntimeException("This is a test");
//        return "Exception";
    }
}
