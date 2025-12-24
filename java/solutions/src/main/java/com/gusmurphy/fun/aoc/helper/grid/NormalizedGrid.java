package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection;
import com.gusmurphy.fun.aoc.helper.geometry.IntegerPoint;

import java.util.List;
import java.util.stream.Stream;

public abstract class NormalizedGrid<E> extends BaseGrid<E> {
    private final int xOffset;
    private final int yOffset;

    public NormalizedGrid(List<List<E>> listRows) {
        super(listRows);
        this.xOffset = 0;
        this.yOffset = 0;
    }

    @Override
    public boolean containsPosition(IntegerPoint position) {
        return super.containsPosition(normalizedPosition(position));
    }

    @Override
    public Line<E> lineFromPositionHeading(IntegerPoint position, CardinalDirection direction) {
        return super.lineFromPositionHeading(normalizedPosition(position), direction);
    }

    @Override
    public Stream<IntegerPoint> positionsOf(E character) {
        return super.positionsOf(character).map(this::denormalizedPosition);
    }

    // Not sure if I'm using "normalized" or "denormalized" correctly here...
    private IntegerPoint normalizedPosition(IntegerPoint position) {
        return new IntegerPoint(position.x() - xOffset, position.y() - yOffset);
    }

    private IntegerPoint denormalizedPosition(IntegerPoint position) {
        return new IntegerPoint(position.x() + xOffset, position.y() - yOffset);
    }
}
