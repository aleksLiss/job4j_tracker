package ru.job4j.search;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class PhoneDictionaryTest {
    @Test
    public void whenFindByName() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Petr", "Arsentev", "534872", "Bryansk")
        );
        ArrayList<Person> persons = phones.find("Petr");
        assertThat(persons.get(0).getSurname()).isEqualTo("Arsentev");
    }

    @Test
    void whenFindByAddressThenReturnEmptyList() {
        PhoneDictionary phones = new PhoneDictionary();
        phones.add(
                new Person("Vova", "Vovkin", "1234567", "Minsk")
        );
        ArrayList<Person> persons = phones.find("Magadan");
        assertThat(persons).isEmpty();
    }
}