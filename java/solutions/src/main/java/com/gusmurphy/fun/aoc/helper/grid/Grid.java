package com.gusmurphy.fun.aoc.helper.grid;

import java.util.stream.Stream;

public interface Grid<T> {
    static Grid<Character> fromFile(String path) {
        return new FileGrid(path);
    }

    Stream<Row<T>> rows();
}
