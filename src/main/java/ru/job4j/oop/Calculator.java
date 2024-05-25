package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int number) {
        return number + x;
    }

    public int multiply(int number) {
        return number * x;
    }

    public static int minus(int number) {
        return number - x;
    }

    public int divide(int number) {
        return number / x;
    }

    public int sumAllOperation(int number) {
        return sum(number)
                + multiply(number)
                + minus(number)
                + divide(number);
    }

    public static void main(String[] args) {
        int number = 5;
        System.out.println(Calculator.sum(number));
        System.out.println(Calculator.minus(number));
        Calculator calculator = new Calculator();
        System.out.println(calculator.multiply(number));
        System.out.println(calculator.divide(number));
        System.out.println(calculator.sumAllOperation(number));
    }
}
