package com.gusmurphy.fun.aoc.year2025.day7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BeamSplitterSolverTest {
    
    @Test
    void noSplitters() {
        var actual = BeamSplitterSolver.countSplitsIn(pathToExample("no-splits"));
        Assertions.assertEquals(0, actual);
    }
    
    @Test
    void singleSplit() {
        var actual = BeamSplitterSolver.countSplitsIn(pathToExample("single-split"));
        Assertions.assertEquals(1, actual);
    }
    
    @Test
    void singleSplitWithOneMiss() {
        var actual = BeamSplitterSolver.countSplitsIn(pathToExample("single-split-with-miss"));
        Assertions.assertEquals(1, actual);
    }
    
    private static String pathToExample(String exampleName) {
        return String.format("src/test/resources/year2025/day7/%s.txt", exampleName);
    }
    
}
