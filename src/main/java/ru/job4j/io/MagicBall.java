package ru.job4j.io;

import java.util.Scanner;
import java.util.Random;

public class MagicBall {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Я великий Оракул. Что ты хочешь узнать? %n");
        String result =  scanner.nextLine();
        int answer = new Random().nextInt(3);
        switch (answer) {
            case 1 -> System.out.println("Да");
            case 2 -> System.out.println("Нет");
            default -> System.out.println("Может быть");
        }
    }
}
