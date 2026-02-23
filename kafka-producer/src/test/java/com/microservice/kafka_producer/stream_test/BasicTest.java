package com.microservice.kafka_producer.stream_test;

import com.microservice.kafka_producer.stream_practice.Basic;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BasicTest {

    @Spy
    private Basic basic;

    @RepeatedTest(2)
    public void basicTest() {
//        int[] randomNumber = generateRandomArray(10, 5, 50);
        int[] randomNumber = {29, 46, 28, 43, 31, 9, 23, 31, 31, 28 };
        int[] result = {28, 31};
        for (int i: randomNumber) {
            System.out.print( i + " ");
        }
        System.out.println();
        basic.findEvenNumbers(randomNumber);
        basic.numberStartWith1(randomNumber);
        basic.removeDuplicate(randomNumber);
        assertArrayEquals(result, basic.getDuplicateElements(randomNumber), "There no excepted error");
        assertEquals(10, basic.findTotalCountElements(randomNumber));
    }

    private int[] generateRandomArray(int size, int min, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        return array;
    }
}
