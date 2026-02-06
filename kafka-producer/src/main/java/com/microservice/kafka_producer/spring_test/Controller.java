package com.microservice.kafka_producer.spring_test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping("getname")
    public String getName() {
        return "Ezekiel";
    }

    public boolean checkBoolean() {
        return true;
    }

    public org.junit.jupiter.api.function.Executable throwException(){
//        throw new RuntimeException("This is a test");
        return "Exception";
    }
}
