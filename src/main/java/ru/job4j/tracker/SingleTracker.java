package ru.job4j.tracker;

import ru.job4j.tracker.pojo.Item;

public class SingleTracker {

    private Tracker tracker = new Tracker();

    private static SingleTracker instance = null;

    private SingleTracker() {
    }

    private static SingleTracker getInstance() {
        if (instance == null) {
            instance = new SingleTracker();
        }
        return instance;
    }

    public Item add(Item item) {
        return tracker.add(item);
    }

    public Item findById(int id) {
        return tracker.findById(id);
    }

    public Item[] findAll() {
        return tracker.findAll();
    }

    public Item[] findByName(String key) {
        return tracker.findByName(key);
    }

    public boolean replace(int id, Item item) {
        return tracker.replace(id, item);
    }

    public void delete(int id) {
        tracker.delete(id);
    }

}
