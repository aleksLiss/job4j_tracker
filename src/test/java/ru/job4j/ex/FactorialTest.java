package ru.job4j.ex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

class FactorialTest {

    @Test
    void whenCalcThenAssertThrows() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Factorial.calc(-2);
                }
        );
        String expected = "Number could not be less than 0";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }

    @Test
    void whenInput5Then120() {
        int input = 5;
        int result = Factorial.calc(5);
        int expected = 120;
        assertThat(result).isEqualTo(expected);
    }
}