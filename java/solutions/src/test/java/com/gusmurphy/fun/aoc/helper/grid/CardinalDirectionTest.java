package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection;
import com.gusmurphy.fun.aoc.helper.geometry.TwoDimensionalPoint;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

import static com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection.*;

class CardinalDirectionTest {

    @ParameterizedTest
    @MethodSource
    void directionFromOnePointToAnother(TwoDimensionalPoint a, TwoDimensionalPoint b, CardinalDirection expected) {
        var actual = CardinalDirection.fromAToB(a, b);
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

    private static TwoDimensionalPoint position(int x, int y) {
        return new TwoDimensionalPoint(x, y);
    }

}