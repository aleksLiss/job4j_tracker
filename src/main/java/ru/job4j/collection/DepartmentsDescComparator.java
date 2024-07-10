package ru.job4j.collection;

import java.util.Comparator;

public class DepartmentsDescComparator implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] leftArr = left.split("/", 2);
        String[] rightArr = right.split("/", 2);
        int result = rightArr[0].compareTo(leftArr[0]);
        return result != 0
                ? result
                : leftArr[leftArr.length - 1].compareTo(rightArr[rightArr.length - 1]);
    }
}
