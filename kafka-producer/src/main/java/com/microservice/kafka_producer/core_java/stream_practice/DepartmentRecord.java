package com.microservice.kafka_producer.core_java.stream_practice;

import java.util.List;

public record DepartmentRecord(String name, List<EmplyeeRecord> employees) {

    public DepartmentRecord {
        name = name.toUpperCase();
    }
    
}
