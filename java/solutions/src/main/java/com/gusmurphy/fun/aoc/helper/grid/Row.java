package com.gusmurphy.fun.aoc.helper.grid;

import java.util.List;
import java.util.Objects;

public class Row<T> {
    private final List<T> elements;
    
    private Row(List<T> elements) {
        this.elements = elements;
    }
    
    public static Row<Character> of(String s) {
        return new Row<>(s.chars().mapToObj(c -> (char) c).toList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Row<?> row = (Row<?>) o;
        return Objects.equals(elements, row.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elements);
    }
}
