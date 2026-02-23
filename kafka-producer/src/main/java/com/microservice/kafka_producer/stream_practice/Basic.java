package com.microservice.kafka_producer.stream_practice;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Basic {

    // Given a list of integers, find all even numbers using Streams.
    public void findEvenNumbers(int[] numbers) {
        System.out.println("Even numbers: ");
        Arrays.stream(numbers)
                .filter(number -> number % 2 == 0)
                .sorted()
                .limit(2)
                .forEach(number -> {System.out.println(number + " ");});
    }

    public void numberStartWith1(int[] numbers) {
        System.out.println("Starting with 1 number: ");
        Arrays.stream(numbers).mapToObj(String::valueOf)
                .filter(str -> str.startsWith("1"))
                .forEach(number -> System.out.print(" "+ number));
    }

    public void removeDuplicate(int[] numbers) {
        System.out.println("Removing duplicates: ");
        Arrays.stream(numbers).distinct().forEach(number -> System.out.print(number + " "));
    }

    public int[] getDuplicateElements(int[] numbers) {
        System.out.println("Duplicate elements: ");
        return Arrays.stream(numbers).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }

    public long findTotalCountElements(int[] numbers) {
        System.out.println("Total elements: ");
        return Arrays.stream(numbers).count();
    }

    private double findSumAndAverage(int[] numbers) {
        System.out.println("Sum and average elements: ");
        return Arrays.stream(numbers).summaryStatistics().getAverage();
    }
}
