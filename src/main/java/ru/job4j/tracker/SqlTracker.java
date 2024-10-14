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
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("db/liquibase.properties")) {
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

    private Item createItem(int id, String name, LocalDateTime created) {
        return new Item(id, name, created);
    }

    @Override
    public Item add(Item item) {
        int id = 0;
        try (Statement statement =
                     connection.prepareStatement("SELECT COUNT(*) FROM tracker")) {
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                id++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (PreparedStatement statement =
                     connection.prepareStatement("INSERT INTO tracker(id, name, created) VALUES(?, ?, ?)")) {
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
                     connection.prepareStatement("UPDATE tracker SET name = ?, created = ? WHERE id = ?")) {
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
                     connection.prepareStatement("DELETE FROM tracker WHERE id = ?")) {
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
                     connection.prepareStatement("SELECT * FROM tracker")) {
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                items.add(createItem(set.getInt("id"),
                        set.getString("name"),
                        LocalDateTime.from(set.getTime("created").toLocalTime())));
            }
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM tracker WHERE name = ?")) {
            statement.setString(1, key);
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                items.add(createItem(set.getInt("id"),
                        set.getString("name"),
                        LocalDateTime.from(set.getTime("created").toLocalTime())));
            }
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM tracker WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet set = statement.getResultSet();
            while (set.next()) {
                item = createItem(set.getInt("id"),
                        set.getString("name"),
                        LocalDateTime.from(set.getTime("created").toLocalTime()));
            }
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return item;
    }

}