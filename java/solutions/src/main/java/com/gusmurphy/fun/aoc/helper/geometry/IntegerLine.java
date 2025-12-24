package com.gusmurphy.fun.aoc.helper.geometry;

import com.gusmurphy.fun.aoc.helper.grid.Line;

import java.util.Optional;
import java.util.stream.Stream;

public class IntegerLine implements Line<IntegerPoint> {
    private final IntegerPoint start;
    private final IntegerPoint end;

    private IntegerLine(IntegerPoint start, IntegerPoint end) {
        this.start = start;
        this.end = end;
    }

    public static IntegerLine between(IntegerPoint start, IntegerPoint end) {
        if (directionBetweenPointsIsNotCardinal(start, end)) {
            throw new IllegalArgumentException("Points must be an exact cardinal direction from each other");
        }
        return new IntegerLine(start, end);
    }

    private static boolean directionBetweenPointsIsNotCardinal(IntegerPoint a, IntegerPoint b) {
        var xDifference = Math.abs(a.x() - b.x());
        var yDifference = Math.abs(a.y() - b.y());

        if (xDifference == 0 || yDifference == 0) {
            return false;
        }

        return xDifference != yDifference;
    }

    @Override
    public Optional<IntegerPoint> get(int i) {
        return Optional.empty();
    }

    @Override
    public Stream<IntegerPoint> elements() {
        return Stream.empty();
    }
}
