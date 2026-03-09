package com.microservice.kafka_producer.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;

public class DeadLock {

    class Payment {
        public void makePayment(Account a, Account b) throws InterruptedException {
            synchronized (a) {
                Thread.sleep(1000);
                synchronized (b) {
                    // make payment
                    System.out.println();
                }
            }
        }
    }

    class Account {

    }

    @Test
    public void testDeadLock() throws InterruptedException {
        Account a = new Account();
        Account b = new Account();
        Payment payment = new Payment();

        Thread t1 = new Thread(()-> {
            try {
                payment.makePayment(a, b);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(()-> {
            try {
                payment.makePayment(b, a);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
//        Thread.sleep(999);
        t2.start();
        t1.join();
        t2.join();

    }
}
