package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection;
import com.gusmurphy.fun.aoc.helper.geometry.IntegerPoint;

import java.util.Map;
import java.util.stream.Stream;

public interface Grid<T> {
    static Grid<Character> fromFile(String path) {
        return new FileGrid<>(path, (c) -> c, ' ');
    }

    static <T> Grid<T> fromPositions(Map<IntegerPoint, T> elementPositions, T emptyValue, int width, int height) {
        return new PositionBasedGrid<>(elementPositions, emptyValue, width, height);
    }

    Stream<Line<T>> rows();

    Stream<Line<T>> columns();

    boolean containsPosition(IntegerPoint position);

    Stream<IntegerPoint> positionsOf(T t);

    Line<T> lineFromPositionHeading(IntegerPoint position, CardinalDirection direction);
}
