package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;
import ru.job4j.tracker.pojo.Item;

import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

class FindByNameActionTest {

    @Test
    public void whenSaveItemThenFoundThisItemByName() {
        Output output = new StubOutput();
        FindByNameAction findByNameAction = new FindByNameAction(output);
        Store tracker = new MemTracker();
        Item item = new Item("Found item");
        item.setCreated(item.getCreated().truncatedTo(ChronoUnit.SECONDS));
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("Found item");
        tracker.add(item);
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder("=== Вывод заявки по имени ===")
                .append(ln)
                .append(item)
                .append(ln);
        assertThat(output.toString())
                .isEqualTo(expected.toString());
    }

    @Test
    public void whenEmptyTrackerThenItemNotFound() {
        Output output = new StubOutput();
        FindByNameAction findByNameAction = new FindByNameAction(output);
        Store tracker = new MemTracker();
        Item item = new Item("Found item");
        item.setCreated(item.getCreated().truncatedTo(ChronoUnit.SECONDS));
        Input input = mock(Input.class);
        findByNameAction.execute(input, tracker);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder("=== Вывод заявки по имени ===")
                .append(ln)
                .append("Заявки с именем: ")
                .append("null")
                .append(" не найдены.")
                .append(ln);
        assertThat(output.toString())
                .isEqualTo(expected.toString());
    }
}