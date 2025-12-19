package com.gusmurphy.fun.aoc.helper.grid;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

class PositionBasedGrid<E> extends BaseGrid<E> {
    PositionBasedGrid(Map<E, Set<GridPosition>> elementPositions, E emptyValue, int width, int height) {
        super(createRowsList(elementPositions, emptyValue, width, height));
    }

    private static <E> List<List<E>> createRowsList(Map<E, Set<GridPosition>> elementPositions, E emptyValue, int width, int height) {
        var rowsList = generateListsOf(emptyValue, width).limit(height).toList();
        elementPositions.forEach(setPositionsForElementInRowsList(rowsList));
        return rowsList;
    }

    private static <E> BiConsumer<E, Set<GridPosition>> setPositionsForElementInRowsList(List<List<E>> rowsList) {
        return (key, value) -> value.forEach(p -> rowsList.get(p.y()).set(p.x(), key));
    }

    private static <E> Stream<List<E>> generateListsOf(E value, int length) {
        return Stream.generate(() -> new ArrayList<>(Collections.nCopies(length, value).stream().toList()));
    }
}
