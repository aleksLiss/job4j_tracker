package ru.job4j.cast;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println("Train goes on rails");
    }

    @Override
    public void showInfo() {
        System.out.println("This is Train");
    }
}
