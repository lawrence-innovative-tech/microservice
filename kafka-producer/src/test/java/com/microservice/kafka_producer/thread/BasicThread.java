package com.microservice.kafka_producer.thread;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class BasicThread {

    @Test
    public void test() throws InterruptedException {

        class InnerSampleThread {

            int counter = 0;

            synchronized void incrementCounter() {
                System.out.println("Increment counter enters");
                LocalDateTime stTime = LocalDateTime.now();
                try {
                    Thread.sleep(5000);
                    this.checkDeadLock();
                }catch(InterruptedException e) {
                    System.out.println(Thread.interrupted());
                }
                System.out.println(STR."Increment counter exits  Duration : \{Duration.between(stTime, LocalDateTime.now())} Thread name \{Thread.currentThread().getName()}");
            }

            synchronized void checkDeadLock() throws InterruptedException {
                System.out.println(STR."Dead lock occurs :\{Thread.currentThread().getName()}");
                Thread.sleep(10000);
            }

            static synchronized void getCounter() {
                System.out.println(STR."Get counter enters : \{Thread.currentThread().getName()}");
                LocalDateTime stTime = LocalDateTime.now();
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    System.out.println(Thread.interrupted());
                }
                System.out.println(STR."Get counter exists : \{Thread.currentThread().getName()} Duration : \{Duration.between(stTime, LocalDateTime.now())}");
                Assertions.assertAll(
                        () -> Assertions.assertEquals("5","5"),
                        () -> Assertions.assertTrue(true)
                );
            }

        }

        InnerSampleThread sampleThread = new InnerSampleThread();
        Runnable runnable = () -> {
            System.out.println(STR."Threat start :\{Thread.currentThread().getName()} \{Thread.currentThread().getState()}");
            InnerSampleThread.getCounter();
            sampleThread.incrementCounter();
        };

        // Thread for different object checks
        Thread differObjectCheck = new Thread(() -> {
            System.out.println(STR."Different object Thread : \{Thread.currentThread().getName()}");
//            new InnerSampleThread().incrementCounter();
            sampleThread.incrementCounter();
        },"different");


//        Thread.startVirtualThread(runnable);
        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);
//        t1.start();
//        t2.start();
//        t3.start();
        differObjectCheck.start();
        differObjectCheck.join();
//        t1.join();

        Thread checkDeadLock = new Thread(() -> {
            try {
                sampleThread.checkDeadLock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "DEAD");
        Thread.sleep(6000);
        checkDeadLock.start();

    }
}
