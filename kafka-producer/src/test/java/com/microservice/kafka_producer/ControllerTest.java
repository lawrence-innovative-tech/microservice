package com.microservice.kafka_producer;

import com.microservice.kafka_producer.spring_test.Controller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void testGetName() {
        System.out.println("Test method");
        Controller controller = new Controller();
        assertEquals(controller.getName(),"Ezekiel");
        assertNotSame(controller.getName(), "Ezekiel1", "not true");
        assertTrue(controller.checkBoolean(), "Test pass");
        assertThrows(RuntimeException.class, controller::throwException);

    }

}
