package ru.job4j.pojo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Ivan Jovanovich Vovkin");
        student.setGroup(104);
        student.setAdmission(LocalDate.now());
        System.out.printf("Student name: %s%n", student.getFullName());
        System.out.printf("Number of group: %d%n", student.getGroup());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.printf("Date of admission: %s", student.getAdmission().format(formatter));
    }
}
