package com.microservice.kafka_producer.thread;

import org.junit.jupiter.api.Test;

public class BasicThread {

    @Test
    public void test() {

        InnerClassThread

           class InnerSampleThread {

            static int counter = 0;

            static synchronized void incrementCounter() {
                System.out.println("Increment counter enters");
                try {
                    Thread.sleep(5000);
                }catch(InterruptedException e) {
                    System.out.println(Thread.interrupted());
                }
                System.out.println("Increment counter exits");
            }

            static synchronized void getCounter() {
                System.out.println("Get counter enters");
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    System.out.println(Thread.interrupted());
                }
                System.out.println("Get counter exits");
            }

        }

    }
}
