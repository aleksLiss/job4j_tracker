package ru.job4j.tracker.action;

import ru.job4j.tracker.*;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.pojo.Item;

public class CreateAction implements UserAction {
    private final Output output;

    public CreateAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Добавить новую заявку";
    }

    @Override
    public boolean execute(Input input, MemTracker tracker) {
        output.println("=== Создание новой заявки ===");
        String name = input.askStr("Enter name: ");
        Item item = new Item(name);
        tracker.add(item);
        output.println("Добавлена заявка: " + item);
        return true;
    }
}
