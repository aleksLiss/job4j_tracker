package ru.job4j.ex;

public class ElementNotFoundException extends Exception {
    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int result = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                result = i;
                break;
            }
        }
        if (result == -1) {
            throw new ElementNotFoundException();
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            ElementNotFoundException.indexOf(new String[]{"1", "2", "3"}, "5");
        } catch (ElementNotFoundException e) {
            e.printStackTrace();
        }
    }
}
