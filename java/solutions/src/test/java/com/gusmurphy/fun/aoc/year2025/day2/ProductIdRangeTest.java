package com.gusmurphy.fun.aoc.year2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class ProductIdRangeTest {

    @Test
    void aStreamCanBeGeneratedForTheRange() {
        var range = new ProductIdRange(3, 7);
        Stream<ProductId> result = range.stream();

        var expected = List.of(
                new ProductId(3),
                new ProductId(4),
                new ProductId(5),
                new ProductId(6),
                new ProductId(7)
        );

        Assertions.assertIterableEquals(expected, result.toList());
    }

    @ParameterizedTest
    @MethodSource("rangesAndExpectedRepeats")
    void repeatedSequenceIdsInRange(ProductIdRange range, List<ProductId> expectedRepeats) {
        var result = range.stream().filter(ProductId::justTwiceRepeatedSequence).toList();
        Assertions.assertIterableEquals(expectedRepeats, result);
    }

    private static Stream<Arguments> rangesAndExpectedRepeats() {
        return Stream.of(
                Arguments.of(new ProductIdRange(11, 22), List.of(new ProductId(11), new ProductId(22))),
                Arguments.of(new ProductIdRange(99, 115), List.of(new ProductId(99)))
        );
    }

}
