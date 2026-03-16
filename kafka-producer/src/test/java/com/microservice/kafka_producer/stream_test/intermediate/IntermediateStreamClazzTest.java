package com.microservice.kafka_producer.stream_test.intermediate;


import com.microservice.kafka_producer.stream_practice.*;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.CheckReturnValue;
import org.mockito.Spy;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.lang.Record;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public final class IntermediateStreamClazzTest extends SealClazzIntermediateSteams {

    @Spy
    private IntermediateStream intermediateStream;

    @Spy
    private FilterSkills filterSkills;

    @DisabledIf(value = "onOff")
    @Test
    public void testIntermediateStream() {
        List<String> stringList = Arrays.asList("apple", "iwi", "anana", "ig", "grape", "waermeln", "pear","hello");
        Map<Character, Long> answerMap = Map.<Character, Long>of('a', 7L, 'e', 5L, 'g', 2L,
                'i', 3L,'l', 2L, 'm', 1L,'n', 3L,
                'p', 4L,'r', 3L,'w', 2L);

        assertThat(answerMap,  is(intermediateStream.findFrequencyElements(stringList)));
        assertThat('p', is(intermediateStream.findFirstRepeatedChar(stringList.getFirst())));

//        assertThat(answerMap,  is(intermediateStream.findFrequencyElement(stringList.getFirst())));

//        Assertions.assertIterableEquals(Collections.singleton(answerMap),
//                Collections.singleton(intermediateStream.findFrequencyElements(stringList)));
    }

    private boolean onOff(){
        return true;
    }

    @Test
    public void getSalary() {
        Map<String, List<String>> actualResult = Map.<String, List<String>>of (
                "DEV", List.of("boot", "html", "ts"),
                "HR", List.of("java", "python", "spring")
        );

        List<DepartmentRecord> departmentsList = getDepartments(1);
        assertThat(actualResult, is(filterSkills.getSkillsDepartmentWise(departmentsList)));

//        For department wise average salary
        List<DepartmentRecord> department1 = new ArrayList<>(getDepartments(1));
        List<DepartmentRecord> department2 = getDepartments(2);
        department1.addAll(department2);
        filterSkills.getDepartmentWiseAvg(department1).entrySet().forEach(System.out::println);
        System.out.println(STR."Highest salaries department wise");
        filterSkills.getHighestSalaryDepartmentWise(department1).entrySet().forEach(System.out::println);
    }

    private static List<DepartmentRecord> getDepartments(int exampleType) {

        return exampleType == 1 ?
        List.of(
                new DepartmentRecord("HR", List.of(
                        new EmplyeeRecord("law", 600000d, List.of("java", "python")),
                        new EmplyeeRecord("agu", 40000d, List.of("Dotnet", "python")),
                        new EmplyeeRecord("ebi", 300000d, List.of("java", "spring"))
                )),
                new DepartmentRecord("DEV", List.of(
                        new EmplyeeRecord("nav", 600000d, List.of("boot", "html")),
                        new EmplyeeRecord("sunil", 40000d, List.of("css", "js")),
                        new EmplyeeRecord("suthan", 300000d, List.of("ts", "boot"))
                ))
        ) :
                List.of(
                        new DepartmentRecord("IT", List.of(
                                new EmplyeeRecord("Agu", 70000d, List.of("Java", "Spring")),
                                new EmplyeeRecord("John", 40000d, List.of("HTML", "CSS"))
                        )),
                        new DepartmentRecord("Finance", List.of(
                                new EmplyeeRecord("Maria", 80000d, List.of("Excel", "Java")),
                                new EmplyeeRecord("David", 55000d, List.of("SQL", "Spring"))
                        ))
                );
    }
}
