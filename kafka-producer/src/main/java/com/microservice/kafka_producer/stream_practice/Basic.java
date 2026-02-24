package com.microservice.kafka_producer.stream_practice;

import java.util.*;
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

    private IntSummaryStatistics findSumAndAverage(int[] numbers) {
        System.out.println("Sum and average elements: ");
        return Arrays.stream(numbers).summaryStatistics();
    }

    public String StringJoinPrefix(List<String> strList, String prefix) {
        System.out.println("StringJoinPrefix: ");
//        strList.stream().map(value -> prefix + value).forEach(System.out::println);
        return String.join("-", strList);
//        return strList.stream().collect(Collectors.joining());
//        return strList.stream().collect(Collectors.joining("-"));
//        return strList.stream().collect(Collectors.joining("-", prefix, "rest" ));
    }

    public void orderLengthBasedStr(List<String> strList) {
        System.out.println("orderLengthBasedStr: ");
//        List<String> sortedList = strList.stream().sorted(Comparator.comparing(String::length).reversed())
//                .map(str ->
//                }).toList();
        List<String> list = List.of("flower", "flood", "flood");

        int minLen = list.stream()
                .mapToInt(String::length)
                .min()
                .orElse(0);

        String prefix = java.util.stream.IntStream.range(0, minLen)
                .takeWhile(i -> list.stream()
                        .map(s -> s.charAt(i))
                        .distinct()
                        .count() == 1)
                .mapToObj(i -> String.valueOf(list.get(0).charAt(i)))
                .collect(java.util.stream.Collectors.joining());
    }
}
