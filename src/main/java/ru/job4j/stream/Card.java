package ru.job4j.stream;

import java.util.stream.Stream;
import static ru.job4j.stream.Suit.*;
import static ru.job4j.stream.Value.*;

enum Suit {
    Diamonds,
    Hearts,
    Spades,
    Clubs
}

enum Value {
    V_6,
    V_7,
    V_8
}

public class Card {
    private Suit suit;
    private Value value;

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }

    public static void main(String[] args) {
        Suit[] suits = {Diamonds, Hearts, Spades, Clubs};
        Value[] values = {V_6, V_7, V_8};
        Stream.of(suits)
                .flatMap(suit -> Stream.of(values)
                        .map(value -> new Card(suit, value)))
                .forEach(System.out::println);
    }
}

