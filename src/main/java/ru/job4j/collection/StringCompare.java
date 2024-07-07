package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int size = Math.min(o1.length(), o2.length());
        for (int i = 0; i < size; i++) {
            int currRes = Character.compare(o1.charAt(i), o2.charAt(i));
            if (currRes > 0) {
                return 1;
            } else if (currRes < 0) {
                return -1;
            }
        }
        if (o1.length() > o2.length()) {
            return 1;
        } else if (o1.length() < o2.length()) {
            return -1;
        }
        return 0;
    }
}
