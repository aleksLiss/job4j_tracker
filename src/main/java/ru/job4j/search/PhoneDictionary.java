package ru.job4j.search;

import java.util.ArrayList;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            String name = person.getName();
            String surname = person.getSurname();
            String phone = person.getPhone();
            String address = person.getAddress();
            if (key.equals(name)
                    || key.equals(surname)
                    || key.equals(phone)
                    || key.equals(address)) {
                result.add(person);
            }
        }
        return result;
    }
}