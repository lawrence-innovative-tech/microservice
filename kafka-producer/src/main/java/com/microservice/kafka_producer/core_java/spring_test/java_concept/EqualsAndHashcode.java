package com.microservice.kafka_producer.core_java.spring_test.java_concept;

import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;

public class EqualsAndHashcode {

    private static HashSet<Employee> employees = new HashSet<>();

    @Getter
    public static class Employee {

        private int id;
        private String name;
        private int age;

        public Employee(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return id + " " + name + " " + age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee employee = (Employee) o;
            return id == employee.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public void checkEqualsAndHashcode(Employee employee) {
        employees.add(employee);
        System.out.println("Size of employees: " + employees.size());

        for (Employee emp : employees) {
            System.out.println(emp.toString());
        }
    }

}
