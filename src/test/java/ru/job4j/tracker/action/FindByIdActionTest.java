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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;

class FindByIdActionTest {

    @Test
    public void whenSaveOneItemThenFoundOneItem() {
        Output output = new StubOutput();
        FindByIdAction findByIdAction = new FindByIdAction(output);
        Store tracker = new MemTracker();
        Item item = new Item("Found item");
        item.setCreated(item.getCreated().truncatedTo(ChronoUnit.SECONDS));
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder("=== Вывод заявки по id ===")
                .append(ln)
                .append(item)
                .append(ln);
        assertThat(output.toString())
                .isEqualTo(expected.toString());
    }

    @Test
    public void whenEmptyTrackerThenFailed() {
        Output output = new StubOutput();
        FindByIdAction findByIdAction = new FindByIdAction(output);
        Store tracker = new MemTracker();
        Item item = new Item("Found item");
        item.setCreated(item.getCreated().truncatedTo(ChronoUnit.SECONDS));
        Input input = mock(Input.class);
        findByIdAction.execute(input, tracker);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder("=== Вывод заявки по id ===")
                .append(ln)
                .append("Заявка с введенным id: ")
                .append(item.getId())
                .append(" не найдена.")
                .append(ln);
        assertThat(output.toString())
                .isEqualTo(expected.toString());

    }
}