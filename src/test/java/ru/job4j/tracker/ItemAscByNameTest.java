package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.pojo.Item;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class ItemAscByNameTest {
    @Test
    void sortAscByNameTest() {
        List<Item> items = new ArrayList<>(List.of(
                new Item(1, "turn on computer"),
                new Item(5, "go to bed"),
                new Item(4, "go outside"),
                new Item(2, "make a coffee"),
                new Item(3, "play a football")
        ));
        List<Item> expected = List.of(
                new Item(4, "go outside"),
                new Item(5, "go to bed"),
                new Item(2, "make a coffee"),
                new Item(3, "play a football"),
                new Item(1, "turn on computer")
        );
        Collections.sort(items, new ItemAscByName());
        assertThat(items).isEqualTo(expected);
    }
}