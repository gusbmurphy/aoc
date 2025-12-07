package com.gusmurphy.fun.aoc.helper.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.helper.grid.GridDirection.*;

public class GridPositionTest {
    
    @ParameterizedTest
    @MethodSource
    void weCanGetAdjacentPositions(GridDirection direction, GridPosition expected) {
        var position = new GridPosition(1, 1);
        var actual = position.toThe(direction);
        Assertions.assertEquals(expected, actual);
    }
    
    private static Stream<Arguments> weCanGetAdjacentPositions() {
        return Stream.of(
                Arguments.of(N, new GridPosition(1, 0)),
                Arguments.of(NE, new GridPosition(2, 0)),
                Arguments.of(E, new GridPosition(2, 1)),
                Arguments.of(SE, new GridPosition(2, 2)),
                Arguments.of(S, new GridPosition(1, 2)),
                Arguments.of(SW, new GridPosition(0, 2)),
                Arguments.of(W, new GridPosition(0, 1)),
                Arguments.of(NW, new GridPosition(0, 0))
        );
    }
    
}
