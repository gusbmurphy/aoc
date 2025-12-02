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
        Assertions.assertEquals(expected, id.justTwiceRepeatedSequence());
    }

    private static Stream<Arguments> idsAndExpectedRepeatResults() {
        return Stream.of(
                Arguments.of(new ProductId(11), true),
                Arguments.of(new ProductId(71), false),
                Arguments.of(new ProductId(1235789), false),
                Arguments.of(new ProductId(1010), true),
                Arguments.of(new ProductId(1188511885), true),
                Arguments.of(new ProductId(38593859), true),
                Arguments.of(new ProductId(222222), true)
        );
    }

}
