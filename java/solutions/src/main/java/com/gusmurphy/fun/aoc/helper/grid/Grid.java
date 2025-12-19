package com.gusmurphy.fun.aoc.helper.grid;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Grid<T> {
    static Grid<Character> fromFile(String path) {
        return new FileGrid<>(path, (c) -> c, ' ');
    }

    static <T> Grid<T> fromFile(String path, Function<Character, T> characterConverter, T emptyValue) {
        return new FileGrid<>(path, characterConverter, emptyValue);
    }

    static <T> Grid<T> fromPositions(Map<T, Set<GridPosition>> positions, T emptyValue, int width, int height) {
        return new PositionBasedGrid<>(positions, emptyValue, width, height);
    }

    Stream<Line<T>> rows();

    Stream<Line<T>> columns();

    boolean containsPosition(GridPosition position);

    Stream<GridPosition> positionsOf(T t);

    Line<T> lineFromPositionHeading(GridPosition position, GridDirection direction);
}
