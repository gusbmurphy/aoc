package com.gusmurphy.fun.aoc.helper.geometry;

import java.util.stream.Stream;

public record TwoDimensionalPoint(int x, int y) {
    public TwoDimensionalPoint toThe(CardinalDirection direction) {
        return switch (direction) {
            case N -> new TwoDimensionalPoint(this.x, this.y - 1);
            case NE -> new TwoDimensionalPoint(this.x + 1, this.y - 1);
            case E -> new TwoDimensionalPoint(this.x + 1, this.y);
            case SE -> new TwoDimensionalPoint(this.x + 1, this.y + 1);
            case S -> new TwoDimensionalPoint(this.x, this.y + 1);
            case SW -> new TwoDimensionalPoint(this.x - 1, this.y + 1);
            case W -> new TwoDimensionalPoint(this.x - 1, this.y);
            case NW -> new TwoDimensionalPoint(this.x - 1, this.y - 1);
        };
    }

    public static Stream<TwoDimensionalPoint> positionsBetweenExclusive(TwoDimensionalPoint a, TwoDimensionalPoint b) {
        var direction = CardinalDirection.fromAToB(a, b);
        return Stream.iterate(a, position -> position.toThe(direction))
                .skip(1)
                .takeWhile(position -> !position.equals(b));
    }
}
