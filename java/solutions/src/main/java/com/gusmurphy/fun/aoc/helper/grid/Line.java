package com.gusmurphy.fun.aoc.helper.grid;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class Line<T> {
    private final List<T> elements;
    
    protected Line(List<T> elements) {
        this.elements = elements;
    }
    
    public static Line<Character> of(String s) {
        return new Line<>(s.chars().mapToObj(c -> (char) c).toList());
    }
    
    public Optional<T> get(int i) {
        return Optional.ofNullable(elements.get(i));
    }

    @Override
    public String toString() {
        return elements.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Line<?> line = (Line<?>) o;
        return Objects.equals(elements, line.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elements);
    }
}
