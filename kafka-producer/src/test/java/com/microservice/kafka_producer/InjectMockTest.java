package com.microservice.kafka_producer;

import com.microservice.kafka_producer.spring_test.Controller;
import com.microservice.kafka_producer.spring_test.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(classes = KafkaProducerApplication.class)
public class InjectMockTest {

    @Mock
    private Service service;

    @InjectMocks
    private Controller controller;

    @BeforeEach
    void setUp() {
        System.out.println("controller: " + controller.hashCode());
        System.out.println("service: " + service.hashCode());
    }

    @Test
    public void injectMockTest() {
        when(controller.getName()).thenReturn("test");
        assertEquals(service.getName(), "test");
    }
}
