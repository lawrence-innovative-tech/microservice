package com.microservice.kafka_producer;

import com.microservice.kafka_producer.spring_test.Controller;
import com.microservice.kafka_producer.spring_test.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

    @InjectMocks
    private Service service;

    @MockBean
    private Controller controller;

    @BeforeEach
    public void setUp() {
        service = mock();
        controller = new Controller(service);
//        ReflectionTestUtils.setField(service, "host", service);
    }

    @Test
    public void testGetName() {
        System.out.println("Test method");

        when(service.getName()).thenReturn("Ezekiel");

        assertEquals(controller.getName(),"Ezekiel", "Test case failed");

        assertSame(service.getName(), "Ezekiel", "not true");

        assertTrue(controller.checkBoolean(), "Test pass");

        assertThrows(RuntimeException.class, controller::throwException);

        verify(service, times(2)).getName();

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    public void testCheckBoolean(int value) {
        service.printNumber(value);
        verify(service, times(1)).printNumber(value);
    }

}
