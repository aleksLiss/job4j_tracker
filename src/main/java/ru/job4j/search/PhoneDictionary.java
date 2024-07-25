package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {

    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public  ArrayList<Person> find(String key) {
        Predicate<Person> isContainName = person -> person.getName().contains(key);
        Predicate<Person> isContainSurname = person -> person.getSurname().contains(key);
        Predicate<Person> isContainPhone = person -> person.getPhone().contains(key);
        Predicate<Person> isContainAddress = person -> person.getAddress().contains(key);
        Predicate<Person> combine = isContainName
                .or(isContainSurname)
                .or(isContainPhone)
                .or(isContainAddress);
        ArrayList<Person> result = new ArrayList<>();
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}