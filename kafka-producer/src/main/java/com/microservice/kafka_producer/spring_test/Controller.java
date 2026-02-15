package com.microservice.kafka_producer.spring_test;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
public class Controller {

    private final Service service;

    @GetMapping("getname")
    public String getName() {
        System.out.println("control class name");
        return service.getName();
    }

    @PostMapping("adduser")
    public Map<String, Object> regrister(@RequestBody UserTestDto newUser){
        return Map.of("status", "200", "message", "User added");
    }
    public boolean checkBoolean() {
        return true;
    }

    public String throwException(){
        throw new RuntimeException("This is a test");
//        return "Exception";
    }
}
