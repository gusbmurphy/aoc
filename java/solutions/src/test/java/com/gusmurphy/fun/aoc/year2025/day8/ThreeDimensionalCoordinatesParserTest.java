package com.gusmurphy.fun.aoc.year2025.day8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ThreeDimensionalCoordinatesParserTest {
    
    @Test
    void coordinatesListIsParsed() {
        var actual = ThreeDimensionalCoordinatesParser.parseFile("src/test/resources/year2025/day8/test-sample.txt");
        var expected = List.of(
                new ThreeDimensionalCoordinates(162,817,812),
                new ThreeDimensionalCoordinates(57,618,57),
                new ThreeDimensionalCoordinates(906,360,560)
        );
        Assertions.assertIterableEquals(expected, actual.toList());
    }
    
}
