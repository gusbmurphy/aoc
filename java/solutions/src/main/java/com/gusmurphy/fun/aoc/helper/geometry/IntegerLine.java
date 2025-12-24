package com.gusmurphy.fun.aoc.helper.geometry;

import com.gusmurphy.fun.aoc.helper.grid.Line;

import java.util.Optional;
import java.util.stream.Stream;

public class IntegerLine implements Line<IntegerPoint> {
    private final IntegerPoint start;
    private final IntegerPoint end;
    private final CardinalDirection direction;

    private IntegerLine(IntegerPoint start, IntegerPoint end, CardinalDirection direction) {
        this.start = start;
        this.end = end;
        this.direction = direction;
    }

    public static IntegerLine between(IntegerPoint start, IntegerPoint end) {
        var direction = CardinalDirection.fromAToB(start, end);
        return new IntegerLine(start, end, direction);
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
