package ru.job4j.lombok;

import lombok.*;

@RequiredArgsConstructor
@EqualsAndHashCode
public class Category {
    @Getter
    @NonNull
    private int id;
    @Getter
    @Setter
    @EqualsAndHashCode.Exclude
    private String name;
}
