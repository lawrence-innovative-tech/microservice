package com.microservice.kafka_producer.stream_test;

import com.microservice.kafka_producer.stream_practice.Basic;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BasicTest {

    @Spy
    private Basic basic;

    @RepeatedTest(1)
        public void basicTest() {

        AtomicInteger inte;
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
        String preFixSubfix = basic.StringJoinPrefix(IntStream.of(randomNumber)
                .boxed().map(String::valueOf).collect(Collectors.toList()), "test");
        System.out.println("prefix subfix: " + preFixSubfix);
        assertEquals(28, basic.secondLargestNumber(result));
//        assertEquals();
        basic.mergeTwoArraysWithSorted(randomNumber, result);

        List<String> stringList = Arrays.asList("apple", "kiwi", "banana", "fig", "grape", "watermelon", "pear");
        List<String> numbrtAndStringList = Arrays.asList("9apple", "kiwi", "2banana", "fig", "8grape", "watermelon", "pear");
        basic.findStringStartWithNumber(numbrtAndStringList);
        //        List<String> list = List.of("preach", "prefix", "prevent", "preview", "preveen", "prem");
        List<String> list = List.of("native", "innovative", "incentive", "inactive", "live");  //
//        List<String> list = List.of("flower", "flood", "flood");
        basic.orderLengthBasedStr(list);

    }

    @Test
    public void testForLoopConcurrentModification() {

        List<String> soureList = new ArrayList<>(List.of("Alice", "Bob", "Charlie"));

        for (String s : soureList) {
            soureList.remove(s);
        }
    }


    private int[] generateRandomArray(int size, int min, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * (max - min + 1)) + min;
        }
        return array;
    }
}
