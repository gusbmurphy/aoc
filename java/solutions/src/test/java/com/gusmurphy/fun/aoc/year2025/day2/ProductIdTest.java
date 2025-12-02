package com.gusmurphy.fun.aoc.year2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class ProductIdTest {

    @ParameterizedTest
    @MethodSource("idsAndExpectedRepeatResults")
    void idContainsSequenceRepeatedTwice(ProductId id, Boolean expected) {
        Assertions.assertEquals(expected, id.containsTwiceRepeatedSequence());
    }

    private static Stream<Arguments> idsAndExpectedRepeatResults() {
        return Stream.of(
                Arguments.of(new ProductId(11), true),
                Arguments.of(new ProductId(71), false)
        );
    }

}
