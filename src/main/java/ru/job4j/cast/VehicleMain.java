package ru.job4j.cast;

public class VehicleMain {
    public static void main(String[] args) {
        Bus bus1 = new Bus();
        Bus bus2 = new Bus();
        Bus bus3 = new Bus();
        Train train1 = new Train();
        Train train2 = new Train();
        Train train3 = new Train();
        Airplane airplane1 = new Airplane();
        Airplane airplane2 = new Airplane();
        Airplane airplane3 = new Airplane();
        Vehicle[] vehicles = new Vehicle[9];
        vehicles[0] = bus1;
        vehicles[1] = bus2;
        vehicles[2] = bus3;
        vehicles[3] = train1;
        vehicles[4] = train2;
        vehicles[5] = train3;
        vehicles[6] = airplane1;
        vehicles[7] = airplane2;
        vehicles[8] = airplane3;
        for (Vehicle vehicle : vehicles) {
            vehicle.showInfo();
            vehicle.move();
        }
    }
}
