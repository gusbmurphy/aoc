package com.gusmurphy.fun.aoc.helper.grid;

import java.util.function.Function;
import java.util.stream.Stream;

public interface Grid<T> {
    static Grid<Character> fromFile(String path) {
        return new FileGrid(path);
    }
    
    static <T> Grid<T> fromFile(String path, Function<Character, T> characterConverter) {
        throw new UnsupportedOperationException();
    }

    Stream<Line<T>> rows();
    Stream<Line<T>> columns();
    boolean containsPosition(GridPosition position);
    Stream<GridPosition> positionsOf(T t);
    Line<T> lineFromPositionHeading(GridPosition position, GridDirection direction);
}
