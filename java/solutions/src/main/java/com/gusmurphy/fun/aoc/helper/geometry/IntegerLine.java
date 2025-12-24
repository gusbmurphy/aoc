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
        return elements().skip(i - 1).findFirst();
    }

    @Override
    public Stream<IntegerPoint> elements() {
        return Stream.iterate(start,
                point -> !point.equals(end.toThe(direction)),
                point -> point.toThe(direction));
    }

    public Optional<IntegerPoint> intersectionWith(IntegerLine other) {
        var denominator = (this.start.x() - this.end.x()) * (other.start.y() - other.end.y()) -
                (this.start.y() - this.end.y()) * (other.start.x() - other.end.x());

        var xNumerator = (this.start.x() * this.end.y() - this.start.y() * this.end.x()) * (other.start.x() - other.end.x())
                - (this.start.x() - this.end.x()) * (other.start.x() * other.end.y() - other.start.y() * other.end.x());

        var yNumerator = (this.start.x() * this.end.y() - this.start.y() * this.end.x()) * (other.start.y() - other.end.y())
                - (this.start.y() - this.end.y()) * (other.start.x() * other.end.y() - other.start.y() * other.end.x());

        var x = xNumerator / denominator;
        var y = yNumerator / denominator;

        return Optional.of(new IntegerPoint(x, y));
    }
}
