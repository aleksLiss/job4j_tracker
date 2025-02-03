package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.ArgumentMatchers.any;
import ru.job4j.tracker.pojo.Item;

class DeleteActionTest {

    @Test
    public void whenDeleteItemSuccessfully() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("Deleted item");
        tracker.add(item);
        DeleteAction deleteAction = new DeleteAction(output);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        deleteAction.execute(input, tracker);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder("=== Удаление заявки ===")
                .append(ln)
                .append("Заявка удалена успешно.")
                .append(ln);
        assertThat(output.toString()).isEqualTo(
            expected.toString()
        );
    }

    @Test
    public void whenDeleteItemFailed() {
        Output output = new StubOutput();
        Store tracker = new MemTracker();
        Item item = new Item("Deleted item");
        DeleteAction deleteAction = new DeleteAction(output);
        Input input = mock(Input.class);
        tracker.add(item);
        String ln = System.lineSeparator();
        deleteAction.execute(input, tracker);
        StringBuilder expected = new StringBuilder("=== Удаление заявки ===")
                .append(ln)
                .append("Ошибка удаления заявки.")
                .append(ln);
        assertThat(output.toString()).isEqualTo(
                expected.toString()
        );
    }
}