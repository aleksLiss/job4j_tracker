package ru.job4j.cast;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Airplane flies by airs");
    }

    @Override
    public void showInfo() {
        System.out.println("This is Airplane");
    }
}
