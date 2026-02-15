package com.microservice.kafka_producer.spring_test;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserTestDto {

    private int id;
    private String name;

}
