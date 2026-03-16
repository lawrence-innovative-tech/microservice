package com.microservice.kafka_producer.stream_practice;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public int secondLargestNumber(int[] numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1).findFirst().orElse(0);
    }

    public String StringJoinPrefix(List<String> strList, String prefix) {
        System.out.println("StringJoinPrefix: ");
//        strList.stream().map(value -> prefix + value).forEach(System.out::println);
        return String.join("-", strList);
//        return strList.stream().collect(Collectors.joining());
//        return strList.stream().collect(Collectors.joining("-"));
//        return strList.stream().collect(Collectors.joining("-", prefix, "rest" ));
    }

    public void mergeTwoArraysDistinctAndSorted(int[] firstArray, int[] secondArray) {
        System.out.println("MergeTwoArraysDistinctAndSorted: ");
        IntStream.concat(Arrays.stream(firstArray), Arrays.stream(secondArray))
                .distinct()
                .sorted()
                .forEachOrdered(value -> System.out.print(STR."\{value} "));
    }

    public void orderLengthBasedStr(List<String> strList) {
        System.out.println("orderLengthBasedStr ");
//        List<String> sortedList = strList.stream().sorted(Comparator.comparing(String::length).reversed())
//                .map(str ->
//                }).toList();


        int minLen = strList.stream()
                .mapToInt(String::length)
                .min()
                .orElse(0);

        String prefix = java.util.stream.IntStream.range(0, minLen)
                .takeWhile(i -> strList.stream()
                        .map(s -> s.charAt(i))
                        .distinct()
                        .count() == 1)
                .mapToObj(i -> String.valueOf(strList.get(0).charAt(i)))
                .collect(java.util.stream.Collectors.joining());
        System.out.println("prefix: " + prefix);


        String prefix1 = strList.stream()
                .reduce((a, b) -> a.substring(0, IntStream.range(0, Math.min(a.length(), b.length()))
                        .filter(i -> a.charAt(i) != b.charAt(i))
                        .findFirst()
                        .orElse(Math.min(a.length(), b.length()))))
                .orElse("");
        System.out.println("prefix1: " + prefix1);

        List<String> mutableStrList = new ArrayList<>(strList);
        List<String> reversedMumtaleList = mutableStrList.stream().map(str -> {
            StringBuilder sb = new StringBuilder(str);
            return sb.reverse().toString();
        }).sorted().toList();//.sorted(Comparator.reverseOrder()).toList();

        System.out.println(reversedMumtaleList);

        String first = reversedMumtaleList.get(0);
        String last = reversedMumtaleList.get(reversedMumtaleList.size() - 1);
        int len = Math.min(first.length(), last.length()), i = 0;

        while (i < len && first.charAt(i) == last.charAt(i)) {
            i++;
        }
        System.out.println("common longest prefix : "+ new StringBuilder(first.substring(0, i)).reverse().toString());

    }

    public void mergeTwoArraysWithSorted(int[] arr1, int[] arr2) {
        System.out.println("String concat : ");
        IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2))
                .distinct()
                .sorted()
                .forEachOrdered(ele -> System.out.print(ele + "-"));
        System.out.println();
//                .toArray();
    }

    public void findStringStartWithNumber(List<String> words){
        System.out.println("String start with number ");
        words.stream().filter(str -> str.charAt(0) <= '9' && str.charAt(0) >= '0'
        ).forEach(str -> System.out.print(str +" "));
    }
}
