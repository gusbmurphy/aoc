package com.gusmurphy.fun.aoc.year2025.day8;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

record PointPair(ThreeDimensionalPoint a, ThreeDimensionalPoint b) {
    PointPair(ThreeDimensionalPoint a, ThreeDimensionalPoint b) {
        var sorted = Stream.of(a, b).sorted(
                Comparator.comparing(ThreeDimensionalPoint::x)
                        .thenComparing(ThreeDimensionalPoint::y)
                        .thenComparing(ThreeDimensionalPoint::z)).toList();
        this.a = sorted.getFirst();
        this.b = sorted.getLast();
    }

    double distanceBetween() {
        return a.distanceTo(b);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PointPair pointPair = (PointPair) o;
        return Objects.equals(a, pointPair.a) && Objects.equals(b, pointPair.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
