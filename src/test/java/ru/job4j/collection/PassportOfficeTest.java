package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PassportOfficeTest {
    @Test
    public void whenTestAddMethod() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport())).isEqualTo(citizen);
    }

    @Test
    void whenTestAddMethodThenReturnFalse() {
        Citizen citizenOne = new Citizen("e145qq", "Ivan Ivanov");
        Citizen citizenTwo = new Citizen("e145qq", "Ivan Ivanov");
        PassportOffice office = new PassportOffice();
        office.add(citizenOne);
        assertThat(office.add(citizenTwo)).isFalse();
    }
}