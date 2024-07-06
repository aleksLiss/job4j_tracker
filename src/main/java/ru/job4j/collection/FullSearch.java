package ru.job4j.collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FullSearch {

    public Set<String> extractNumber(List<Task> taskList) {
        Set<String> numbers = new HashSet<>();
        for (Task task : taskList) {
            numbers.add(task.getNumber());
        }
        return numbers;
    }
}
