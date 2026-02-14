package com.microservice.kafka_producer;

import com.microservice.kafka_producer.spring_test.Controller;
import com.microservice.kafka_producer.spring_test.Service;

import lombok.Getter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@WebMvcTest
@ContextConfiguration(classes = KafkaProducerApplicationTests.class)
public class ControllerTest {

    @Mock
    private Service service;

//    @MockBean
//    private Service serviceMock;

    @InjectMocks
    private Controller controller;

//    @Spy
//    private Service service1;

    @Autowired
    private Controller controller1;

//    @BeforeAll
//    public void injectBean(){
////        ReflectionTestUtils.setField(controller, "service", service);
//        service = mock();
//        controller = mock();
//    }

    @BeforeEach
    public void setUp() {
//        service = mock();
//        controller = new Controller(serviceMock);
        System.out.println("controller :"+controller.hashCode());
//        ReflectionTestUtils.setField(service, "host", service);
    }

    @RepeatedTest(value = 2)
    public void testGetName() {
        System.out.println("Test method - "+this.hashCode());

        when(service.getName()).thenReturn("Ezekiel");

//        assertEquals("Ezekiel", controller1.getName());

        assertEquals(controller.getName(),"Ezekiel", () -> {
            return "Test has failed through getName()";
        });

        assertSame(service.getName(), "Ezekiel", "not true");

        assertTrue(controller.checkBoolean(), "Test pass");

        assertThrows(RuntimeException.class, controller::throwException);

        verify(service, times(2)).getName();

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 5, 7})
    public void testCheckBoolean(int value) {
//        doReturn(service1).when(service1).getName();
//        when(service1.getName()).then(invocation -> invocation.getArgument(0));
//        String val = service1.getName();
//        System.out.println(val);
//        assertEquals("actual test message", val);
        //.thenReturn("Spy test");
//        assertEquals(service1.getName(), "actual test message", "actual test message");
//        service1.printNumber(value);
//        verify(service, times(1)).printNumber(value);

    }

    @ParameterizedTest
    @ValueSource(strings = {"ArrayList", "HashMap", "TreeMap", "TreeSet"})
    public void checkParameterTest(String value) {
        System.out.println("Print parameter test :"+value);
    }

    @ParameterizedTest
    @CsvSource(value = {"test1|test2", "test3|test4"}, delimiter = '|')
    public void checkCsvTest(String value1, String value2) {
        System.out.println("Print csv test :"+value1 + " - "+value2);
    }

    @ParameterizedTest
    @MethodSource(value = "checkMethodTest")
    public void checkMethodTest(String value) {
        System.out.println("Print method test :"+value);
    }

    private static Stream<Arguments> checkMethodTest() {
        return Stream.of("test1", "test2", "test3").map(Arguments::of);
    }

    @RepeatedTest(value = 5)
    public void testAdd() {
        System.out.println("Test method - "+this.hashCode());
    }

    @Nested
    @DisplayName("checking display name")
    public class NestedTestChecking {

        @BeforeAll
        static void setUpBeforeClass() throws Exception {
            System.out.println("Setup before class");
        }

        @BeforeEach
        public void setUp() throws Exception {
            System.out.println("Setup before each");
        }

        @Test
        public void testCheckBoolean() {
            System.out.println("Test method - "+this.hashCode());
        }
    }

    @TestFactory
    Stream<DynamicNode> tests() {
        System.out.println("check dynamic tests");
        return Stream.of(
                DynamicContainer.dynamicContainer(
                        "Math Tests",
                        Stream.of(
                                DynamicTest.dynamicTest("2+2", () -> assertEquals(4, 2+2)),
                                DynamicTest.dynamicTest("3+3", () -> assertEquals(6, 3+3)),
                                DynamicTest.dynamicTest("summa", this::testAdd)
                        )
                )
        );
    }

    @Test
    public void testStream() {

        class Employee {

            private String name;
            @Getter
            private double salary;

            Employee(String name, double salary) {
                this.name = name;
                this.salary = salary;
            }

            @Override
            public String toString() {
                return "Employee ( " + name + ", "+ salary +" )";
            }
        }

        List<Employee> employees = Arrays.asList(
                new Employee("raja", 50000),
                new Employee("ebi", 10000),
                new Employee("ezekiel", 100000),
                new Employee("nish", 70000.0),
                new Employee("rajan", 500000.0));

        int limit = 3;
        List<Employee> emp = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).limit(limit).toList();
        System.out.println(emp.get(limit - 1));

        Optional<Employee> emp1 = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(limit - 1).findFirst();
        assertTrue(emp1.isPresent(), "Employee not found");
        System.out.println(emp1.get());
    }

    @RepeatedTest(value = 5)
    public void checkRepeatedTest(RepetitionInfo repetitionInfo) {
        service.printNumber(repetitionInfo.getCurrentRepetition());
//        System.out.println("info :"+ );
        verify(service, times(1))
                .printNumber(repetitionInfo.getCurrentRepetition());
    }
}
