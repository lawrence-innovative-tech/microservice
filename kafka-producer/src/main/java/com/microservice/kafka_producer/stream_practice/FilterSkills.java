package com.microservice.kafka_producer.stream_practice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.StandardException;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FilterSkills {

    public List<String> getAllUniqueSkills(List<DepartmentRecord> skills) {
       return skills.stream()
                .flatMap(employee -> employee.employees().stream()
                        .filter(employee1 -> employee1.salary() > 50000d))
                .flatMap(employee -> employee.skills().stream())
                .distinct().sorted().toList();
    }

    public Map<String, List<String>> getSkillsDepartmentWise(List<DepartmentRecord> skills) {

        Map<String, List<String>> skillsListDepartmentWise = skills.stream()
                .flatMap(employee -> {
                    Map<String, List<String>> uniqueSkillList = new HashMap<>();
                    List<String> skillList = employee.employees().stream()
                            .filter(salary -> salary.salary() > 50000)
                            .flatMap(emp -> emp.skills().stream()).distinct().sorted().toList();
                    uniqueSkillList.put(employee.name(), skillList);
                    return uniqueSkillList.entrySet().stream();
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        for (Map.Entry<String, List<String>> entry : skillsListDepartmentWise.entrySet()) {
            String skillName = entry.getKey();
            List<String> skillList = entry.getValue();
            System.out.println(STR."Name: \{skillName}, List: \{skillList}");
        }
Throwable
        //String string = SQL."Select * from identity_cache where id = \{skillsListDepartmentWise.keySet().iterator().next()}";
        return skillsListDepartmentWise;

    }
    
}
