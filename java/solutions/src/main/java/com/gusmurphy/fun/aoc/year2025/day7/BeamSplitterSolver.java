package com.gusmurphy.fun.aoc.year2025.day7;

import com.gusmurphy.fun.aoc.helper.grid.Grid;

public class BeamSplitterSolver {
    private static final char SPLITTER = '^';
    
    public static long countSplitsIn(String fileName) {
        return Grid.fromFile(fileName)
                .rows()
                .mapToLong(r -> r.elements().filter(c -> c.equals(SPLITTER)).count())
                .sum();
    }
}
