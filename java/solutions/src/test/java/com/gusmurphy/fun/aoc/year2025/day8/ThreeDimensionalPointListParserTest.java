package com.gusmurphy.fun.aoc.year2025.day8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ThreeDimensionalPointListParserTest {
    
    @Test
    void coordinatesListIsParsed() {
        var actual = ThreeDimensionalPointListParser.parseFile("src/test/resources/year2025/day8/test-sample.txt");
        var expected = List.of(
                new ThreeDimensionalPoint(162,817,812),
                new ThreeDimensionalPoint(57,618,57),
                new ThreeDimensionalPoint(906,360,560)
        );
        Assertions.assertIterableEquals(expected, actual.toList());
    }
    
}
