package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.TwoDimensionalPoint;

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
    public boolean containsPosition(TwoDimensionalPoint position) {
        return super.containsPosition(normalizedPosition(position));
    }

    @Override
    public Line<E> lineFromPositionHeading(TwoDimensionalPoint position, GridDirection direction) {
        return super.lineFromPositionHeading(normalizedPosition(position), direction);
    }

    @Override
    public Stream<TwoDimensionalPoint> positionsOf(E character) {
        return super.positionsOf(character).map(this::denormalizedPosition);
    }

    // Not sure if I'm using "normalized" or "denormalized" correctly here...
    private TwoDimensionalPoint normalizedPosition(TwoDimensionalPoint position) {
        return new TwoDimensionalPoint(position.x() - xOffset, position.y() - yOffset);
    }

    private TwoDimensionalPoint denormalizedPosition(TwoDimensionalPoint position) {
        return new TwoDimensionalPoint(position.x() + xOffset, position.y() - yOffset);
    }
}
