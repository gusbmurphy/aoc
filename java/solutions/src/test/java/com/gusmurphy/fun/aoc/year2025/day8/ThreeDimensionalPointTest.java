package com.gusmurphy.fun.aoc.year2025.day8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreeDimensionalPointTest {
    
    @Test
    void distanceBetweenTwoCoordinates() {
        var a = new ThreeDimensionalPoint(7, 4, 3);
        var b = new ThreeDimensionalPoint(17, 6, 2);
        Assertions.assertEquals(10.246950765959598, a.distanceTo(b));
    }
    
}
