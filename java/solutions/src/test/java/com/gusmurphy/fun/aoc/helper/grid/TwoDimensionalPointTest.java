package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection;
import com.gusmurphy.fun.aoc.helper.geometry.TwoDimensionalPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection.*;

public class TwoDimensionalPointTest {
    
    @ParameterizedTest
    @MethodSource
    void weCanGetAdjacentPositions(CardinalDirection direction, TwoDimensionalPoint expected) {
        var position = new TwoDimensionalPoint(1, 1);
        var actual = position.toThe(direction);
        Assertions.assertEquals(expected, actual);
    }
    
    private static Stream<Arguments> weCanGetAdjacentPositions() {
        return Stream.of(
                Arguments.of(N, new TwoDimensionalPoint(1, 0)),
                Arguments.of(NE, new TwoDimensionalPoint(2, 0)),
                Arguments.of(E, new TwoDimensionalPoint(2, 1)),
                Arguments.of(SE, new TwoDimensionalPoint(2, 2)),
                Arguments.of(S, new TwoDimensionalPoint(1, 2)),
                Arguments.of(SW, new TwoDimensionalPoint(0, 2)),
                Arguments.of(W, new TwoDimensionalPoint(0, 1)),
                Arguments.of(NW, new TwoDimensionalPoint(0, 0))
        );
    }
    
}
