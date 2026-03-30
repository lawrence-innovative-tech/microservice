package com.microservice.kafka_producer.stream_practice.concept_oriented;

import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsClazz {

    public void finaMaxSalariesFromDepartment(List<ConceptEmplyee> employeeList) {

        System.out.println("Department wise salary ");
        employeeList.stream()
                .collect(Collectors.groupingBy(ConceptEmplyee::department,
                        Collectors.summingDouble(ConceptEmplyee::salary)))
                .entrySet().forEach(System.out::println);

        System.out.println("Department wise max salary using optional ");
        employeeList.stream()
                .collect(Collectors.groupingBy(ConceptEmplyee::department, LinkedHashMap::new,
                        Collectors.maxBy(Comparator.comparing(ConceptEmplyee::salary))))
                .entrySet().forEach(System.out::println);

        System.out.println("Without Collectors maxBy ");
        employeeList.stream()
                .collect(Collectors.groupingBy(ConceptEmplyee::department, LinkedHashMap::new,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparing(ConceptEmplyee::salary))
                                , emp -> emp.orElseGet(null).salary()
                        )))
                .entrySet()
                .forEach(System.out::println);

        System.out.println("Partition By ");
        employeeList.stream()
//                .mapToInt()
                .sorted(Comparator.comparingDouble(ConceptEmplyee::salary))
                .distinct()
                .collect(Collectors.partitioningBy(emp -> emp.salary() > 0.5,
                        Collectors.maxBy(Comparator.comparingDouble(ConceptEmplyee::salary))))
                .entrySet().forEach(System.out::println);

    }

    public void prefixSequence(List<String> findFrequency){

        String prefixValue = findFrequency.stream()
                .reduce((a, b) -> a.substring(0, IntStream.range(0,
                        Math.min(a.length(), b.length()))
                        .filter(i -> a.charAt(i) != b.charAt(i))
                        .findFirst().orElse(Math.min(a.length(), b.length()))))
                .orElse("");

        System.out.println(STR."Prefix value \{prefixValue}");

        int minLentgh = findFrequency.stream()
                .mapToInt(String::length)
                .min().orElse(0);

        String prefixChar = IntStream.range(0,
                        findFrequency.stream()
                                .mapToInt(String::length)
                                .min().orElse(0))
                .takeWhile(i -> findFrequency
                        .stream()
                        .map(str -> str.charAt(i))
                        .distinct()
                        .count() == 1)
                .mapToObj(i -> String.valueOf(findFrequency.getFirst().charAt(i)))
                .collect(Collectors.joining(","));

        System.out.println(STR."Using Take while \{prefixChar}");

        String secoundPrefixTech = IntStream.range(0, minLentgh).takeWhile(index ->
                findFrequency.stream().
                map(str1 -> str1.charAt(index))
                .distinct().count() == 1)
                .mapToObj(i -> String.valueOf(findFrequency.getFirst().charAt(i)))
                .collect(Collectors.joining());
        System.out.println(STR."Second prefix tech \{secoundPrefixTech}");

        String longestString = findFrequency.stream()
                .collect(Collectors.groupingBy(String::length))
                        .entrySet().stream().max(Map.Entry.<Integer, List<String>>comparingByKey())
                .stream().map(Map.Entry::getValue).flatMap(Collection::stream)
                .findFirst().orElse(null);
        System.out.println(STR."Longest String \{longestString}");

        String longestSubString = findFrequency.stream()
                .reduce((a, b) -> (a.length() > b.length() ? a : b))
                .orElse(null);

        System.out.println(STR."Longest subString using reduce \{longestSubString}");
    }

    public void longestCommonSuffix(List<String> stringList) {
        String suffix = stringList.stream().map(str -> new StringBuilder(str).reverse().toString())
                .reduce((a, b) -> a.substring(0, IntStream.range(0, Math.min(a.length(), b.length()))
                        .filter(i -> a.charAt(i) != b.charAt(i))
                        .findFirst().orElse(Math.min(a.length(), b.length()))))
                .orElse(null);
        System.out.println(STR."Longest common suffix \{suffix}");


    }
}
