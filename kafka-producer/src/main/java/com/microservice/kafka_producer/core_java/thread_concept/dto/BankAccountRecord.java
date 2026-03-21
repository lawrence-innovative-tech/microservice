package com.microservice.kafka_producer.core_java.thread_concept.dto;

import java.io.Serializable;

public record BankAccountRecord(String accountNumber, long balance) implements Serializable {

    public void transfer(final BankAccountRecord bankAccountRecord) {
        this.balance = this.balance + bankAccountRecord.balance();
    }
}
