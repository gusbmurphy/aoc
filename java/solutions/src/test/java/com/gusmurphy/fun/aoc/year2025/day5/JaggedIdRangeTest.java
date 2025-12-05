package com.gusmurphy.fun.aoc.year2025.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class JaggedIdRangeTest {

    @ParameterizedTest
    @MethodSource
    void mergingRanges(JaggedIdRange a, JaggedIdRange b, JaggedIdRange expected) {
        var actualForwards = a.mergeWith(b);
        var actualBackwards = b.mergeWith(a);

        Assertions.assertEquals(expected, actualForwards);
        Assertions.assertEquals(expected, actualBackwards);
    }

    private static Stream<Arguments> mergingRanges() {
        return Stream.of(
                Arguments.of(
                        new JaggedIdRange(2, 3),
                        new JaggedIdRange(8, 9),
                        new JaggedIdRange(2L, 3L, 8L, 9L)
                ),
                Arguments.of(
                        new JaggedIdRange(2, 9),
                        new JaggedIdRange(9, 12),
                        new JaggedIdRange(2L, 12L)
                ),
                Arguments.of(
                        new JaggedIdRange(2, 9),
                        new JaggedIdRange(3, 12),
                        new JaggedIdRange(2L, 12L)
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void rangeIncludes(long l, boolean expected) {
        var actual = new JaggedIdRange.IdRange(4, 6).includes(l);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> rangeIncludes() {
        return Stream.of(
                Arguments.of(3, false),
                Arguments.of(7, false),
                Arguments.of(4, true),
                Arguments.of(5, true),
                Arguments.of(6, true)
        );
    }
}