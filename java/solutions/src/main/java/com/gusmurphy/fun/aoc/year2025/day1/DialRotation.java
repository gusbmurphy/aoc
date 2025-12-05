package com.gusmurphy.fun.aoc.year2025.day1;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public record DialRotation(Direction direction, int distance) {
    public Stream<DialRotation> steps() {
        return IntStream.range(0, distance)
                .mapToObj(i -> new DialRotation(direction, 1));
    }

    public enum Direction {
        LEFT, RIGHT
    }
}
