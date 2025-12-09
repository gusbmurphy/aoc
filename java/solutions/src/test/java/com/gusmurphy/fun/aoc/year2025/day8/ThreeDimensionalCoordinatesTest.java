package com.gusmurphy.fun.aoc.year2025.day8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ThreeDimensionalCoordinatesTest {
    
    @Test
    void distanceBetweenTwoCoordinates() {
        var a = new ThreeDimensionalCoordinates(7, 4, 3);
        var b = new ThreeDimensionalCoordinates(17, 6, 2);
        Assertions.assertEquals(10.246950765959598, a.distanceTo(b));
    }
    
}
