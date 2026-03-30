package com.microservice.kafka_producer.stream_practice.concept_oriented;

public record ConceptEmplyee(String name, double salary, String department) {

    public ConceptEmplyee {
        if(salary < 0){
            throw new IllegalArgumentException("Salary cannot be negative");
        }
    }

    @Override
    public String toString(){
        return STR."ConceptEmplyee(\{name}, \{salary}, \{department})";
    }
}
