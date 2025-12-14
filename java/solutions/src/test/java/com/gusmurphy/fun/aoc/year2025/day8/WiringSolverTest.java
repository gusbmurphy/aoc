package com.gusmurphy.fun.aoc.year2025.day8;

import com.gusmurphy.fun.aoc.ResourcePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WiringSolverTest {
    
    @Test
    void part1Example() {
        long actual = WiringSolver.productOfThreeLargestCircuits(ResourcePath.toTestResource(8, "example"), 10);
        Assertions.assertEquals(40, actual);
    }
    
    @Test
    void part1MyInput() {
        long actual = WiringSolver.productOfThreeLargestCircuits(ResourcePath.toRealInput(8), 1000);
        Assertions.assertEquals(42315, actual);
    }
}
