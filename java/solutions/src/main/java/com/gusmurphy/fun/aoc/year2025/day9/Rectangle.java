package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.geometry.IntegerPoint;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Rectangle {
    private final IntegerPoint c1;
    private final IntegerPoint c2;

    public Rectangle(IntegerPoint c1, IntegerPoint c2) {
        var sorted = Stream.of(c1, c2)
                .sorted(Comparator.comparing(IntegerPoint::x).thenComparing(IntegerPoint::y))
                .toList();
        this.c1 = sorted.getFirst();
        this.c2 = sorted.getLast();
    }

    public long area() {
        long width = Math.abs(c1.x() - c2.x()) + 1;
        long height = Math.abs(c1.y() - c2.y()) + 1;
        return width * height;
    }

    public Stream<IntegerPoint> allPositions() {
        return xRangeBetweenCorners().boxed().flatMap(x ->
                yRangeBetweenCorners().boxed().map(y -> new IntegerPoint(x, y)));
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
