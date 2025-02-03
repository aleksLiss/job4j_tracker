package ru.job4j.tracker.action;

import org.junit.jupiter.api.Test;
import ru.job4j.tracker.MemTracker;
import ru.job4j.tracker.Store;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.output.Output;
import ru.job4j.tracker.output.StubOutput;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ExitActionTest {

    @Test
    public void whenExitProgrammeSucceccfully() {
        Output output = new StubOutput();
        ExitAction exitAction = new ExitAction(output);
        Input input = mock(Input.class);
        Store tracker = new MemTracker();
        exitAction.execute(input, tracker);
        String expected = "=== Завершение программы ===\n";
        assertThat(output.toString()).isEqualTo(expected);
    }

}