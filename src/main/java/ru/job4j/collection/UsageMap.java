package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("aleksei@mail.ru", "PupkinIvanIvanovich");
        map.put("ivanovich@gmail.com", "IvanovIvanIvanovich");
        map.put("vova64@yandex.ru", "VovkinVovaNikolaevich");
        map.put("vika44@yandex.ru", "IvanovaVikaViktorovna");
        map.put("aleksei@mail.ru", "PupkinIvanIvanovich");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
