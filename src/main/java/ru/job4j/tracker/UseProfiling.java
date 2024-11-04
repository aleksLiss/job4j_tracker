package ru.job4j.tracker;

import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;


public class UseProfiling {
    public static void main(String[] args) {
        try {
            System.out.println(ProcessHandle.current().pid());
            Thread.sleep(4000);
            Output output = new StubOutput();
            Store tracker = new MemTracker();
            CreateManyItems createManyItems = new CreateManyItems(output);
            DeleteAllItems deleteAllItems = new DeleteAllItems(output);
            createManyItems.execute(new ConsoleInput(), tracker);
            deleteAllItems.execute(new ConsoleInput(), tracker);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}