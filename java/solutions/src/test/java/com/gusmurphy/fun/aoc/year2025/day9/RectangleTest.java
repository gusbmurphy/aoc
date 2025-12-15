package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.grid.GridPosition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RectangleTest {

    @ParameterizedTest
    @MethodSource
    void areaIsCalculatedCorrectly(GridPosition a, GridPosition b, long expected) {
        var rectangle = new Rectangle(a, b);
        assertEquals(expected, rectangle.area());
    }
    
    private static Stream<Arguments> areaIsCalculatedCorrectly() {
        return Stream.of(
                Arguments.of(new GridPosition(7, 1), new GridPosition(11, 7), 35),
                Arguments.of(new GridPosition(2, 5), new GridPosition(9, 7), 24),
                Arguments.of(new GridPosition(7, 3), new GridPosition(2, 3), 6)
        );
    }
    
}