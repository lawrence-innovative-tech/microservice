package com.microservice.kafka_producer.core_java.stream_practice;

import java.util.List;

public record EmplyeeRecord(String name, Double salary,List<String> skills)
    implements Comparable<EmplyeeRecord> {

    @Override
    public String toString() {
        return STR."EmplyeeRecord{name='\{name}\{'\''}, salary=\{salary}, skills=\{skills}\{'}'}";
    }

    @Override
    public int compareTo(EmplyeeRecord o) {
        return name.compareTo(o.name());
    }
}

