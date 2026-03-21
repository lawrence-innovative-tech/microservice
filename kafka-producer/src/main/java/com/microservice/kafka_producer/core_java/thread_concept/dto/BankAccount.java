package com.microservice.kafka_producer.core_java.thread_concept.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BankAccount {

    private String accountNumber;
    private long balance;

    public void deposit(long amount) {}

}
