package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection;
import com.gusmurphy.fun.aoc.helper.geometry.TwoDimensionalPoint;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

public interface Grid<T> {
    static Grid<Character> fromFile(String path) {
        return new FileGrid<>(path, (c) -> c, ' ');
    }

    static <T> Grid<T> fromFile(String path, Function<Character, T> characterConverter, T emptyValue) {
        return new FileGrid<>(path, characterConverter, emptyValue);
    }

    static <T> Grid<T> fromPositions(Map<TwoDimensionalPoint, T> elementPositions, T emptyValue, int width, int height) {
        return new PositionBasedGrid<>(elementPositions, emptyValue, width, height);
    }

    Stream<Line<T>> rows();

    Stream<Line<T>> columns();

    boolean containsPosition(TwoDimensionalPoint position);

    Stream<TwoDimensionalPoint> positionsOf(T t);

    Line<T> lineFromPositionHeading(TwoDimensionalPoint position, CardinalDirection direction);
}
