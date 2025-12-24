package com.gusmurphy.fun.aoc.helper.geometry;

import java.util.stream.Stream;

public record IntegerPoint(int x, int y) {
    public IntegerPoint toThe(CardinalDirection direction) {
        return switch (direction) {
            case N -> new IntegerPoint(this.x, this.y - 1);
            case NE -> new IntegerPoint(this.x + 1, this.y - 1);
            case E -> new IntegerPoint(this.x + 1, this.y);
            case SE -> new IntegerPoint(this.x + 1, this.y + 1);
            case S -> new IntegerPoint(this.x, this.y + 1);
            case SW -> new IntegerPoint(this.x - 1, this.y + 1);
            case W -> new IntegerPoint(this.x - 1, this.y);
            case NW -> new IntegerPoint(this.x - 1, this.y - 1);
        };
    }

    public static Stream<IntegerPoint> positionsBetweenExclusive(IntegerPoint a, IntegerPoint b) {
        var direction = CardinalDirection.fromAToB(a, b);
        return Stream.iterate(a, position -> position.toThe(direction))
                .skip(1)
                .takeWhile(position -> !position.equals(b));
    }
}
