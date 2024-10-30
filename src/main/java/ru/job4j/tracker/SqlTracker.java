package ru.job4j.tracker;

import ru.job4j.tracker.pojo.Item;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {

    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
        init();
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("db/liquibase_test.properties")) {
            Properties config = new Properties();
            config.load(input);
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

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    private Item createItemFromResultSet(ResultSet set) {
        Item item = null;
        try {
            item = new Item(set.getInt("id"),
                    set.getString("name"),
                    set.getTimestamp("created")
                            .toLocalDateTime()
                            .withNano(0));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }

    @Override
    public Item add(Item item) {
        int id = 0;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM items")) {
            statement.execute();
            ResultSet set = statement.getResultSet();
            if (set != null) {
                while (set.next()) {
                    id++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO items(id, name, created) VALUES(?, ?, ?)")) {
            long millis = System.currentTimeMillis();
            LocalDateTime currTime = new Timestamp(millis).toLocalDateTime();
            statement.setInt(1, id);
            statement.setString(2, item.getName());
            statement.setTimestamp(3, Timestamp.valueOf(currTime));
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        try (PreparedStatement statement =
                     connection.prepareStatement("UPDATE items SET name = ?, created = ? WHERE id = ?")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.setInt(3, id);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement =
                     connection.prepareStatement("DELETE FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM items")) {
            statement.execute();
            while (statement.getResultSet().next()) {
                items.add(createItemFromResultSet(statement.getResultSet()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM items WHERE name = ?")) {
            statement.setString(1, key);
            statement.execute();
            while (statement.getResultSet().next()) {
                items.add(createItemFromResultSet(statement.getResultSet()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM items WHERE id = ?")) {
            statement.setInt(1, id);
            statement.execute();
            while (statement.getResultSet().next()) {
                item = (createItemFromResultSet(statement.getResultSet()));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }
}