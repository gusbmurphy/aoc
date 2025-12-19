package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.LineReader;
import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LargestRectangleSolver {
    public static long largestRectangleAreaInFile(String example) {
        var positions = positionsInFile(example)
                .collect(Collectors.toSet());

        return positions.stream()
                .flatMap(p1 -> positions.stream()
                        .filter(p -> p != p1)
                        .map(p2 -> new Rectangle(p1, p2)))
                .mapToLong(Rectangle::area)
                .max().orElseThrow();
    }

    private static GridPosition gridPositionFromString(String s) {
        var digits = Stream.of(s.split(",")).map(Integer::valueOf).toList();
        return new GridPosition(digits.getFirst(), digits.getLast());
    }

    public static long largestRectangleAreaWithinTilePerimeterInFile(String path) {
        var redTilePositions = positionsInFile(path).toList();
        var floor = TiledFloor.withRedTilesAtPositions(redTilePositions);
        return floor.sizeOfLargestRedCorneredRectangleInTiledArea();
    }

    private static Stream<GridPosition> positionsInFile(String example) {
        return LineReader.readAllLinesFrom(example)
                .map(LargestRectangleSolver::gridPositionFromString);
    }
}
