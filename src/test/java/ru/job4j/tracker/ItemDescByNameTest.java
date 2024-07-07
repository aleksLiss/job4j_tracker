package ru.job4j.tracker;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.pojo.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class ItemDescByNameTest {

    @Test
    void sortDescByNameTest() {
        List<Item> items = new ArrayList<>(List.of(
            new Item(5, "Surf internet"),
            new Item(2, "Go to sleep"),
            new Item(3, "Do a homework"),
            new Item(4, "Help mother"),
            new Item(1, "Eat breakfast")
        ));
        List<Item> expected = List.of(
                new Item(5, "Surf internet"),
                new Item(4, "Help mother"),
                new Item(2, "Go to sleep"),
                new Item(1, "Eat breakfast"),
                new Item(3, "Do a homework")

        );
        Collections.sort(items, new ItemDescByName());
        assertThat(items).isEqualTo(expected);
    }

}