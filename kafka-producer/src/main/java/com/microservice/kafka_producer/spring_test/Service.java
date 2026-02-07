package com.microservice.kafka_producer.spring_test;

@org.springframework.stereotype.Service
public class Service {

    public String getName() {
        System.out.println("test service name");
        return "actual test message";
    }

    public void printNumber(int number) {
        System.out.println("test service number " + number);
    }
}
