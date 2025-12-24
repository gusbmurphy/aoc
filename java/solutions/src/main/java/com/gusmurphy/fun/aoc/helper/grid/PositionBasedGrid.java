package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.IntegerPoint;

import java.util.*;

class PositionBasedGrid<E> extends NormalizedGrid<E> {
    PositionBasedGrid(Map<IntegerPoint, E> elementPositions, E emptyValue, int width, int height) {
        super(createRowsList(elementPositions, emptyValue, width, height));
    }

    private static <E> List<List<E>> createRowsList(Map<IntegerPoint, E> elementPositions, E emptyValue, int width, int height) {
        var rowsList = new ArrayList<List<E>>();
        for (int y = 0; y < height; y++) {
            var row = new ArrayList<E>();
            for (int x = 0; x < width; x++) {
                var element = elementPositions.getOrDefault(new IntegerPoint(x, y), emptyValue);
                row.add(element);
            }
            rowsList.add(row);
        }
        return rowsList;
    }

}
