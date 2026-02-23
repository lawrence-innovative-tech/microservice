package com.microservice.kafka_producer;

import com.microservice.kafka_producer.spring_test.Controller;
import com.microservice.kafka_producer.spring_test.Service;
import com.microservice.kafka_producer.spring_test.java_concept.EqualsAndHashcode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = KafkaProducerApplication.class)
public class InjectMockTest {

    @Mock
    private Service service;

    @InjectMocks
    private Controller controller;

    @Spy
    private EqualsAndHashcode equalsAndHashcode;

    @BeforeEach
    void setUp() {
//        System.out.println("controller: " + controller.hashCode());
//        System.out.println("service: " + service.hashCode());
    }

    @Test
    public void injectMockTest() {
        when(controller.getName()).thenReturn("test");
        assertEquals(service.getName(), "test");
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void contributionCheck(int value) {
        assertEquals(value, value);
    }

    @ParameterizedTest
    @MethodSource(value = "contributionCheck")
    public void hashMapEqualsAndHashcode(EqualsAndHashcode.Employee employee) {
        equalsAndHashcode.checkEqualsAndHashcode(employee);
    }

    private static Stream<EqualsAndHashcode.Employee> contributionCheck() {
        return Stream.of(
                new EqualsAndHashcode.Employee(1, "Nish", 30),
                new EqualsAndHashcode.Employee(2, "Nish22", 30),
                new EqualsAndHashcode.Employee(1, "Nish", 30)
        );
    }
}
