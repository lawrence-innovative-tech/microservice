package com.microservice.kafka_producer.stream_test.concept_stream;

import com.microservice.kafka_producer.stream_practice.concept_oriented.ConceptEmplyee;
import com.microservice.kafka_producer.stream_practice.concept_oriented.StreamsClazz;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ConceptEvalution {

    @Spy
    private StreamsClazz streamsClazz;

    @Test
    public void test() {
        List<ConceptEmplyee> employeeList = List.of(
                new ConceptEmplyee("Ebi", generateRandom(), "IT"),
                new ConceptEmplyee("Ebinish",  generateRandom(), "DEV"),
                new ConceptEmplyee("Ezikiel",  generateRandom(), "QA"),
                new ConceptEmplyee("Isabella",  generateRandom(), "DEV"),
                new ConceptEmplyee("Anto",  generateRandom(), "IT")
        );

        streamsClazz.finaMaxSalariesFromDepartment(employeeList);
        streamsClazz.prefixSequence(List.of("flower", "flowz", "floor", "flashez","flashex"));
        streamsClazz.longestCommonSuffix(List.of("native", "live"));
    }

    private double generateRandom(){
        return Math.random();

    }
}
