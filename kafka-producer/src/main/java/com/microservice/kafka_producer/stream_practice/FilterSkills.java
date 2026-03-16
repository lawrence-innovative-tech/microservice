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

    public Map<String, Double> getDepartmentWiseAvg(List<DepartmentRecord> skills) {
         return skills.stream()
                .collect(Collectors.toMap(DepartmentRecord::name,
                        dept -> dept.employees().stream().collect(Collectors.averagingDouble(EmplyeeRecord::salary))))
                 .entrySet().stream()
                 .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }

    public Map<String, List<EmplyeeRecord>> getHighestSalaryDepartmentWise(List<DepartmentRecord> skills) {
        return skills.stream().collect(Collectors.toMap(DepartmentRecord::name,
                dept -> dept.employees().stream().sorted(Comparator.comparing(EmplyeeRecord::salary).reversed())
                        .limit(2).toList(), (e1, e2) -> e1, LinkedHashMap::new));
    }

    private Double filterSalary(EmplyeeRecord emp){
        return emp.salary();
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

        String string = STR."Select * from identity_cache where id = \{skillsListDepartmentWise.keySet().iterator().next()}";
        return skillsListDepartmentWise;

    }
    
}
