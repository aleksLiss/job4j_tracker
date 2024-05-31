package ru.job4j.polymorphism;

public class Bus implements Transport {
    @Override
    public void go() {
        System.out.println("Bus is starting...");
    }

    @Override
    public void getCountOfPassengers(int countOfPassengers) {
        System.out.printf("Count passengers into bus: %d%n", countOfPassengers);
    }

    @Override
    public double refuel(int volumeOfFuel) {
        return volumeOfFuel * 2.15;
    }
}
