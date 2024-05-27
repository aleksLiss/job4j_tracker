package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        int index = indexOf(id);
        return index != -1 ? items[index] : null;
    }

    public Item[] findAll() {
        Item[] result = new Item[items.length];
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                result[size] = items[i];
                size++;
            }
        }
        return Arrays.copyOf(result, size);
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[items.length];
        int size = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                if (items[i].getName().equals(key)) {
                    result[size] = items[i];
                    size++;
                }
            }
        }
        return Arrays.copyOf(result, size);
    }

    public boolean replace(int id, Item item) {
        item.setId(id);
        if (findById(id) != null) {
            int index = indexOf(id);
            items[indexOf(id)] = item;
            return true;
        }
        return false;
    }

    // 1 2 3 4 5

    public void delete(int id) {
        int index = indexOf(id);
        if (index != -1) {
            items[index] = null;
            System.arraycopy(items, 3, items, 2, size - index - 1);
            items[size - 1] = null;
            size--;
        }
    }

    private int indexOf(int id) {
        int result = -1;
        for (int index = 0; index < size; index++) {
            if (items[index].getId() == id) {
                result = index;
                break;
            }
        }
        return result;
    }

}