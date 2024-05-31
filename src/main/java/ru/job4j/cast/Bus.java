package ru.job4j.cast;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Bus goes on the roads");
    }

    @Override
    public void showInfo() {
        System.out.println("This is Bus.");
    }
}
