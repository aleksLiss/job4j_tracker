package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.assertj.core.api.Assertions.assertThat;

public class JobTest {
    @Test
    void whenCompareByNameByAsc() {
        Comparator<Job> cmpNamePriority = new JobAscByName();
        int result = cmpNamePriority.compare(
                new Job("Go outside", 0),
                new Job("Make a coffee", 1)
        );
        assertThat(result).isLessThan(0);
    }

    @Test
    void whenCompareByNameByDesc() {
        Comparator<Job> cmpNamePriority = new JobDescByName();
        int result = cmpNamePriority.compare(
                new Job("Help mother", 2),
                new Job("Make a coffee", 0)
        );
        assertThat(result).isGreaterThan(0);
    }

    @Test
    void whenCompareByPriorityByAsc() {
        Comparator<Job> cmpNamePriority = new JobAscByPriority();
        int result = cmpNamePriority.compare(
                new Job("Go outside", 0),
                new Job("Make a coffee", 1)
        );
        assertThat(result).isLessThan(0);
    }

    @Test
    void whenCompareByPriorityByDesc() {
        Comparator<Job> cmpNamePriority = new JobDescByPriority();
        int result = cmpNamePriority.compare(
                new Job("Help mother", 2),
                new Job("Make a coffee", 0)
        );
        assertThat(result).isLessThan(0);
    }

    @Test
    public void whenCompatorByNameAndProrityThenGreatSecondObject() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompatorByNameAndProrityThenGreatFirstObject() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Help mother", 2),
                new Job("Surf internet", 0)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenCompatorByNameAndProrityThenEqualsObjects() {
        Comparator<Job> cmpNamePriority = new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Fix bug", 1),
                new Job("Fix bug", 1)
        );
        assertThat(rsl).isEqualTo(0);
    }
}