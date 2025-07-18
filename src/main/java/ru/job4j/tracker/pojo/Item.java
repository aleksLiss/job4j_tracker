package ru.job4j.tracker.pojo;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class Item {

    private int id;
    private String name;
    private LocalDateTime created = LocalDateTime.now().withNano(0);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMMM-EEEE-yyyy HH:mm:ss");

}