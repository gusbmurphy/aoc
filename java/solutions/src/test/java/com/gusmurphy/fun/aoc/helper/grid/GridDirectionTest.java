package com.gusmurphy.fun.aoc.helper.grid;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static com.gusmurphy.fun.aoc.helper.grid.GridDirection.*;

class GridDirectionTest {

    @ParameterizedTest
    @MethodSource
    void directionFromOnePointToAnother(GridPosition a, GridPosition b, GridDirection expected) {
        var actual = GridDirection.fromAToB(a, b);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> directionFromOnePointToAnother() {
        return Stream.of(
                Arguments.of(position(1, 1), position(1, 2), S),
                Arguments.of(position(1, 1), position(1, 0), N),
                Arguments.of(position(1, 1), position(0, 1), W),
                Arguments.of(position(1, 1), position(2, 1), E),
                Arguments.of(position(1, 1), position(0, 0), NW),
                Arguments.of(position(1, 1), position(2, 0), NE),
                Arguments.of(position(1, 1), position(0, 2), SW),
                Arguments.of(position(1, 1), position(2, 2), SE)
        );
    }

    private static GridPosition position(int x, int y) {
        return new GridPosition(x, y);
    }

}