package com.gusmurphy.fun.aoc.helper.grid;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

class PositionBasedGrid<E> extends BaseGrid<E> {
    PositionBasedGrid(Map<GridPosition, E> elementPositions, E emptyValue, int width, int height) {
        super(createRowsList(elementPositions, emptyValue, width, height));
    }

    private static <E> List<List<E>> createRowsList(Map<GridPosition, E> elementPositions, E emptyValue, int width, int height) {
        var rowsList = new ArrayList<List<E>>();
        for (int y = 0; y < height; y++) {
            var row = new ArrayList<E>();
            for (int x = 0; x < width; x++) {
                var element = elementPositions.getOrDefault(new GridPosition(x, y), emptyValue);
                row.add(element);
            }
            rowsList.add(row);
        }
        return rowsList;
    }

    private static <E> BiConsumer<E, Set<GridPosition>> setPositionsForElementInRowsList(List<List<E>> rowsList) {
        return (key, value) -> value.forEach(p -> rowsList.get(p.y()).set(p.x(), key));
    }

    private static <E> Stream<List<E>> generateListsOf(E value, int length) {
        return Stream.generate(() -> new ArrayList<>(Collections.nCopies(length, value).stream().toList()));
    }
}
