package com.microservice.kafka_producer.core_java.thread_concept.lockingmechanism;

import com.microservice.kafka_producer.core_java.thread_concept.dto.BankAccount;
import com.microservice.kafka_producer.core_java.thread_concept.dto.BankAccountRecord;

public class SynchroninzedLocking {

    public synchronized void transferAmount(BankAccount source, BankAccount destination, int amount) {

        synchronized (source) {
            synchronized (destination) {
                source.setBalance(source.getBalance() + amount);
                destination.setBalance(destination.getBalance() - amount);
            }
        }
    }
}
