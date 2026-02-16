package com.microservice.kafka_producer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = KafkaProducerApplication.class)
class KafkaProducerApplicationTests {

//	@Test
//	void contextLoads() {
//
//	}

    public static void main(String arg[])
    {
        SpringApplication.run(KafkaProducerApplicationTests.class, arg);
    }

}
