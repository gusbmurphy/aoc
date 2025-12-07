package com.gusmurphy.fun.aoc.helper.grid;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringLine implements Line<Character> {
    private final List<Character> elements;
    
    protected StringLine(List<Character> elements) {
        this.elements = elements;
    }
    
    public static Line<Character> of(String s) {
        return new StringLine(s.chars().mapToObj(c -> (char) c).toList());
    }
    
    public Optional<Character> get(int i) {
        return Optional.ofNullable(elements.get(i));
    }
    
    public Stream<Character> elements() {
        return elements.stream();
    }

    @Override
    public String toString() {
        return elements.stream().map(String::valueOf).collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StringLine that = (StringLine) o;
        return Objects.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(elements);
    }
}
