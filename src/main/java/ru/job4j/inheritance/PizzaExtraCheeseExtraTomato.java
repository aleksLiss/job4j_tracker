package ru.job4j.inheritance;

public class PizzaExtraCheeseExtraTomato extends PizzaExtraCheese {
    public String name() {
        return super.name() + " with extra tomato.";
    }
}
