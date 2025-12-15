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
    
}
