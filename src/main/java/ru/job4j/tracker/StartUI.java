package ru.job4j.tracker;

import java.time.format.DateTimeFormatter;

public class StartUI {

    private Item item;

    public StartUI(int id) {
        this.item = new Item();
    }

    public Item getItem() {
        return item;
    }

    public static void main(String[] args) {
        StartUI startUI = new StartUI(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");
        System.out.println(startUI.getItem().getCreated().format(formatter));
        System.out.println(new Item());
    }
}
