package com.gusmurphy.fun.aoc.helper.geometry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IntegerLineTest {

    @ParameterizedTest
    @MethodSource
    void linesForNonCardinalDirectionsCantBeMade(IntegerPoint other) {
        var point = new IntegerPoint(3, 3);
        assertThrows(IllegalArgumentException.class, () -> IntegerLine.between(point, other));
    }

    private static Stream<Arguments> linesForNonCardinalDirectionsCantBeMade() {
        return Stream.of(
                Arguments.of(new IntegerPoint(5, 2)),
                Arguments.of(new IntegerPoint(5, 4)),
                Arguments.of(new IntegerPoint(1, 2)),
                Arguments.of(new IntegerPoint(1, 4)),
                Arguments.of(new IntegerPoint(2, 5)),
                Arguments.of(new IntegerPoint(4, 5)),
                Arguments.of(new IntegerPoint(2, 1)),
                Arguments.of(new IntegerPoint(4, 1))
        );
    }

    @Test
    void pointsOnLineCanBeRetrieved() {
        var line = IntegerLine.between(
                new IntegerPoint(1, 1),
                new IntegerPoint(3, 3)
        );

        var actual = line.elements().toList();
        var expected = List.of(
                new IntegerPoint(1, 1),
                new IntegerPoint(2, 2),
                new IntegerPoint(3, 3)
        );
        assertIterableEquals(expected, actual);
    }

}