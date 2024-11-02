package ru.job4j.tracker;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.tracker.pojo.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.*;

public class SqlTrackerTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterAll
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @AfterEach
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item(1, "item");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result).isEqualTo(item);
    }

    @Test
    public void whenSaveItemAndFindByNameThenMustBeSame() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item(1, "item1");
        tracker.add(item);
        Item result = tracker.findByName("item1").get(0);
        assertThat(result).isEqualTo(item);
    }

    @Test
    public void whenSaveSomeItemsAndFindAllThenFindAllItems() {
        SqlTracker tracker = new SqlTracker(connection);
        Item itemOne = new Item(1, "item1");
        Item itemTwo = new Item(2, "item2");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        List<Item> expected = List.of(itemOne, itemTwo);
        List<Item> result = tracker.findAll();
        assertThat(result).isNotEmpty().hasSize(2).containsSequence(expected);
    }

    @Test
    public void whenSaveItemAndReplaceToOtherItemThenMustBeReplaced() {
        SqlTracker tracker = new SqlTracker(connection);
        Item firstIt = new Item(1,"item1");
        Item secondIt = new Item(2,"item2");
        tracker.add(firstIt);
        tracker.replace(firstIt.getId(), secondIt);
        Item result = tracker.findById(1);
        assertThat(result).isEqualTo(secondIt);
    }

    @Test
    public void whenSaveItemAndDeleteItThenFindByIdMustBeNull() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item1");
        tracker.add(item);
        tracker.delete(item.getId());
        Item result = tracker.findById(item.getId());
        assertThat(result).isNull();
    }
}