package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.pojo.Item;

import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

class FindAllActionTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    @Test
    public void whenEmptyStorageThenFailed() {
        Output output = new StubOutput();
        FindAllAction findAllAction = new FindAllAction(output);
        Store tracker = new MemTracker();
        Input input = mock(Input.class);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder("=== Вывод всех заявок ===")
                .append(ln)
                .append("Хранилище еще не содержит заявок")
                .append(ln);
        findAllAction.execute(input, tracker);
        assertThat(output.toString())
                .isEqualTo(expected.toString());
    }

    @Test
    public void whenSaveTwoItemsThenReturnThisItems() {
        Output output = new StubOutput();
        FindAllAction findAllAction = new FindAllAction(output);
        Store tracker = new MemTracker();
        Input input = mock(Input.class);
        Item item1 = new Item("Item 1");
        Item item2 = new Item("Item 2");
        item1.setCreated(item1.getCreated().truncatedTo(ChronoUnit.SECONDS));
        item2.setCreated(item2.getCreated().truncatedTo(ChronoUnit.SECONDS));
        tracker.add(item1);
        tracker.add(item2);
        findAllAction.execute(input, tracker);
        String ln = System.lineSeparator();
        StringBuilder expeceted = new StringBuilder("=== Вывод всех заявок ===")
                .append(ln)
                .append(item1)
                .append(ln)
                .append(item2)
                .append(ln);
        assertThat(output.toString())
                .isEqualTo(expeceted.toString());
    }
}

