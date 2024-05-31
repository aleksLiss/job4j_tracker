package ru.job4j.io;

import java.util.Scanner;

public class Mathes {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок" : "Второй игрок";
            System.out.println(player + " введите число от 1 до 3:");
            int mathes = Integer.parseInt(input.nextLine());
            if ((mathes > 0 && mathes <= Math.min(3, count))) {
                turn = !turn;
                count -= mathes;
                System.out.println("Остаток спичек: " + count);
            } else {
                System.out.println(player
                        + " выполнил неверный ввод!");
                System.out.println("Введите число от 1 до 3"
                        + " и что бы число было не больше "
                        + count
                        + ".");
            }
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
