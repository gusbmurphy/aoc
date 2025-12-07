package com.gusmurphy.fun.aoc.year2025.day7;

import com.gusmurphy.fun.aoc.helper.grid.Grid;
import com.gusmurphy.fun.aoc.helper.grid.GridDirection;

public class BeamSplitterSolver {
    private static final Character SPLITTER = '^';
    private static final char START = 'S';

    public static long countSplitsIn(String fileName) {
        var grid = Grid.fromFile(fileName);
        var startPosition = grid.positionsOf(START).findFirst().orElseThrow();
        
        return grid.lineFromPositionHeading(startPosition, GridDirection.S).elements()
                .filter(SPLITTER::equals)
                .count();
    }
}
