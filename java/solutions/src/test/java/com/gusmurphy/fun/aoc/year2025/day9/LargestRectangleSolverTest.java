package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.ResourcePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LargestRectangleSolverTest {

    @Test
    void part1Example() {
        long actual = LargestRectangleSolver.largestRectangleAreaInFile(
                ResourcePath.toTestResource(9, "example"));

        Assertions.assertEquals(50, actual);
    }

    @Test
    void part1MyInput() {
        long actual = LargestRectangleSolver.largestRectangleAreaInFile(
                ResourcePath.toRealInput(9));

        Assertions.assertEquals(4773451098L, actual);
    }
    
    @Test
    void part2Example() {
        long actual = LargestRectangleSolver.largestRectangleAreaWithinTilePerimeterInFile(
                ResourcePath.toTestResource(9, "example"));

        Assertions.assertEquals(24, actual);
    }
}
