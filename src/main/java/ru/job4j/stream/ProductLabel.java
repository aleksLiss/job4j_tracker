package ru.job4j.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .flatMap(Stream::ofNullable)
                .filter(product -> product.getStandard() - product.getActual() > -1)
                .filter(product -> product.getStandard() - product.getActual() < 4)
                .map(product -> new Label(product.getName(), (float) (product.getPrice() * 0.5)).toString())
                .collect(Collectors.toList());
    }
}