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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

class CreateActionTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

    @Test
    public void whenItemWasCreateSuccessfully() {
        Store tracker = new MemTracker();
        Item item = new Item("Created item");
        item.setCreated(item.getCreated().truncatedTo(ChronoUnit.SECONDS));
        Output output = new StubOutput();
        CreateAction createAction = new CreateAction(output);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        createAction.execute(input, tracker);
        String ln = System.lineSeparator();
        StringBuilder expected = new StringBuilder("=== Создание новой заявки ===")
                .append(ln)
                .append("Добавлена заявка: ")
                .append("Item{id=")
                .append(1)
                .append(", name=")
                .append("'")
                .append(item.getName())
                .append("'")
                .append(", created=")
                .append(FORMATTER.format(item.getCreated()))
                .append("}")
                .append(ln);
        assertThat(output.toString()).isEqualTo(
                expected.toString()
        );
    }
}