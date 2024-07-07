package ru.job4j.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        List<String> leftArr = new ArrayList<>(List.of(left.split("/")));
        List<String> rightArr = new ArrayList<>(List.of(right.split("/")));
        int size = Math.min(leftArr.size(), rightArr.size());
        int result;
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                result = rightArr.get(i).compareTo(leftArr.get(i));
            } else {
                result = leftArr.get(i).compareTo(rightArr.get(i));
            }
            if (result != 0) {
                return result;
            }
        }
        return Integer.compare(leftArr.size(), rightArr.size());
    }
}
