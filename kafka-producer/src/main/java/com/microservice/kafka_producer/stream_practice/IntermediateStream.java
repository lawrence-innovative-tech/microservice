package com.microservice.kafka_producer.stream_practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateStream {

    public Map<Character, Long> findFrequencyElements (List<String> words) {

        return words.stream().flatMap(string -> string.chars().boxed())
                .collect(Collectors.groupingBy(integer -> (char) integer.intValue(), Collectors.counting()));
    }

    public Map<Character, Integer> findFrequencyElement (String words) {

        Map<Character, Integer> map = new HashMap<>();
        for (char c : words.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        return map;

    }
}
