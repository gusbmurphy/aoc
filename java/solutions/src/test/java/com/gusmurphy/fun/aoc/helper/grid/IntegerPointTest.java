package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection;
import com.gusmurphy.fun.aoc.helper.geometry.IntegerPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection.*;

public class IntegerPointTest {
    
    @ParameterizedTest
    @MethodSource
    void weCanGetAdjacentPositions(CardinalDirection direction, IntegerPoint expected) {
        var position = new IntegerPoint(1, 1);
        var actual = position.toThe(direction);
        Assertions.assertEquals(expected, actual);
    }
    
    private static Stream<Arguments> weCanGetAdjacentPositions() {
        return Stream.of(
                Arguments.of(N, new IntegerPoint(1, 0)),
                Arguments.of(NE, new IntegerPoint(2, 0)),
                Arguments.of(E, new IntegerPoint(2, 1)),
                Arguments.of(SE, new IntegerPoint(2, 2)),
                Arguments.of(S, new IntegerPoint(1, 2)),
                Arguments.of(SW, new IntegerPoint(0, 2)),
                Arguments.of(W, new IntegerPoint(0, 1)),
                Arguments.of(NW, new IntegerPoint(0, 0))
        );
    }
    
}
