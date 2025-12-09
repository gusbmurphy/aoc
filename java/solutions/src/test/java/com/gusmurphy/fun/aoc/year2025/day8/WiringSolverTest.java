package com.gusmurphy.fun.aoc.year2025.day8;

import com.gusmurphy.fun.aoc.ResourcePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WiringSolverTest {
    
    @Test
    void part1Example() {
        int actual = WiringSolver.productOfThreeLargestCircuits(ResourcePath.toTestResource(8, "example"), 10);
        Assertions.assertEquals(40, actual);
    }
    
}
