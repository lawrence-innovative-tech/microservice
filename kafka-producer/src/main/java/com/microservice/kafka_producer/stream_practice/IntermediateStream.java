package com.microservice.kafka_producer.stream_practice;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntermediateStream {

    public Map<Character, Long> findFrequencyElements (List<String> words) {
        System.out.println(STR."Source List \{words}");
        List<String> sortedWords = words.stream().sorted().toList();
        System.out.println(STR."Sorted List \{sortedWords}");
        return words.stream().flatMap(string -> string.chars().mapToObj(val -> (char) val))
                .collect(Collectors.groupingBy(character -> character, Collectors.counting()));
    }

    public Map<Character, Integer> findFrequencyElement (String words) {

        Map<Character, Integer> map = new HashMap<>();
        for (char c : words.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    public Character findFirstRepeatedChar(String words) {
        return words.chars().mapToObj(c -> (char) c).toList()
                .stream().collect(Collectors.groupingBy(e -> e, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(ele -> ele.getValue() != 1).map(Map.Entry::getKey).findFirst().orElse(null);
    }

    public Character findFirstNonRepeatedChar(String words) {
        return words.chars().mapToObj(ch -> (char) ch).toList()
                .stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(ele -> ele.getValue() == 1)
                .map(Map.Entry::getKey).findFirst().orElse(null);
    }
}
