package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullSearch {

    private Set<String> numbers;

    public FullSearch() {
        this.numbers = new HashSet<>();
    }

    public Set<String> extractNumber(List<Task> taskList) {
        for (Task task : taskList) {
            numbers.add(task.getNumber());
        }
        return numbers;
    }
}
