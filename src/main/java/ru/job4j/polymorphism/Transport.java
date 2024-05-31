package ru.job4j.polymorphism;

public interface Transport {
    void go();

    void getCountOfPassengers(int countOfPassengers);

    double refuel(int volumeOfFuel);
}
