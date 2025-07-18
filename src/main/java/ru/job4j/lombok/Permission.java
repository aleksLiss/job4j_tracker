package ru.job4j.lombok;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;
import ru.job4j.pojo.License;

import java.util.List;

@Builder(builderMethodName = "start")
@ToString
@Getter
public class Permission {
    private int id;
    private String name;
    @Singular("accessTo")
    private List<String> rules;
}

class Test {
    public static void main(String[] args) {
        var permission = Permission.start()
                .id(1)
                .name("permit")
                .accessTo("rule_1")
                .accessTo("rule_2")
                .accessTo("rule_3")
                .build();
    }
}