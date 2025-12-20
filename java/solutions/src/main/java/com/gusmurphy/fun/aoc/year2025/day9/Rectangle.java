package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Rectangle {
    private final GridPosition c1;
    private final GridPosition c2;

    public Rectangle(GridPosition c1, GridPosition c2) {
        var sorted = Stream.of(c1, c2)
                .sorted(Comparator.comparing(GridPosition::x).thenComparing(GridPosition::y))
                .toList();
        this.c1 = sorted.getFirst();
        this.c2 = sorted.getLast();
    }

    public long area() {
        long width = Math.abs(c1.x() - c2.x()) + 1;
        long height = Math.abs(c1.y() - c2.y()) + 1;
        return width * height;
    }

    public Stream<GridPosition> allPositions() {
        return xRangeBetweenCorners().boxed().flatMap(x ->
                yRangeBetweenCorners().boxed().map(y -> new GridPosition(x, y)));
    }

    private IntStream xRangeBetweenCorners() {
        var sortedXValues = Stream.of(c1.x(), c2.x()).sorted().toList();
        return IntStream.rangeClosed(sortedXValues.getFirst(), sortedXValues.getLast());
    }

    private IntStream yRangeBetweenCorners() {
        var sortedYValues = Stream.of(c1.y(), c2.y()).sorted().toList();
        return IntStream.rangeClosed(sortedYValues.getFirst(), sortedYValues.getLast());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Objects.equals(c1, rectangle.c1) && Objects.equals(c2, rectangle.c2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(c1, c2);
    }
}
