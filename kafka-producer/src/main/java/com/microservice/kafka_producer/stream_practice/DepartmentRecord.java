package com.microservice.kafka_producer.stream_practice;

import java.util.List;

public record DepartmentRecord(String name, List<EmplyeeRecord> employees) {

    public DepartmentRecord {
        name = name.toUpperCase();
    }
    
}
