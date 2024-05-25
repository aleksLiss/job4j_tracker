package ru.job4j.oop;

public class Cat {

    private String name;

    private String food;

    public void show() {
        System.out.printf("%s ate %s.%n", this.name, this.food);
    }

    public void giveNick(String nick) {
        this.name = nick;
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public String sound() {
        String voice = "may-may";
        return voice;
    }

    public static void main(String[] args) {
        System.out.println("There is gav's food.");
        Cat gav = new Cat();
        gav.giveNick("gav");
        gav.eat("kotleta");
        gav.show();
        System.out.println("There is black's food.");
        Cat black = new Cat();
        black.giveNick("black");
        black.eat("fish");
        black.show();
    }
}
