package com.gusmurphy.fun.aoc.year2025.day8;

import com.gusmurphy.fun.aoc.ResourcePath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class WiringSolverTest {
    
    @Test
    void part1Example() {
        int actual = WiringSolver.productOfThreeLargestCircuits(ResourcePath.toTestResource(8, "example"), 10);
        Assertions.assertEquals(40, actual);
    }
    
    @Test
    @Disabled
    void part1MyInput() {
        int actual = WiringSolver.productOfThreeLargestCircuits(ResourcePath.toRealInput(8), 1000);
        Assertions.assertEquals(0, actual);
    }
}
