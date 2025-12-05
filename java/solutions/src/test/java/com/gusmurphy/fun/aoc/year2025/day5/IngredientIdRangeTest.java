package com.gusmurphy.fun.aoc.year2025.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class IngredientIdRangeTest {

    @ParameterizedTest
    @MethodSource
    void twoNonOverLappingRangesCombinedStayTheSameWhenMerged(IngredientIdRange a, IngredientIdRange b) {
        var expected = Stream.of(
                new IngredientIdRange(1, 4),
                new IngredientIdRange(6, 8)
        );
        Stream<IngredientIdRange> actual = a.mergeWith(b);

        Assertions.assertIterableEquals(expected.toList(), actual.toList());
    }

    private static Stream<Arguments> twoNonOverLappingRangesCombinedStayTheSameWhenMerged() {
        return Stream.of(
                Arguments.of(
                        new IngredientIdRange(1, 4),
                        new IngredientIdRange(6, 8)
                ),
                Arguments.of(
                        new IngredientIdRange(6, 8),
                        new IngredientIdRange(1, 4)
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void twoAdjacentRangesCombineToBeASingleContinuousRange(IngredientIdRange a, IngredientIdRange b) {
        var expected = Stream.of(
                new IngredientIdRange(7, 20)
        );
        Stream<IngredientIdRange> actual = a.mergeWith(b);

        Assertions.assertIterableEquals(expected.toList(), actual.toList());
    }

    private static Stream<Arguments> twoAdjacentRangesCombineToBeASingleContinuousRange() {
        return Stream.of(
                Arguments.of(
                        new IngredientIdRange(7, 10),
                        new IngredientIdRange(11, 20)
                ),
                Arguments.of(
                        new IngredientIdRange(11, 20),
                        new IngredientIdRange(7, 10)
                )
        );
    }

    @Test
    void whenOneRangeSubsumesTheOtherWeGetJustTheLargerRange() {
        var a = new IngredientIdRange(1, 8);
        var b = new IngredientIdRange(3, 5);

        var expected = Stream.of(new IngredientIdRange(1, 8));
        var actual = a.mergeWith(b);

        Assertions.assertIterableEquals(expected.toList(), actual.toList());
    }

    @Test
    void whenRangesAreOtherwiseOverlappingWeGetTheCombination() {
        var a = new IngredientIdRange(1, 8);
        var b = new IngredientIdRange(3, 21);

        var expected = Stream.of(new IngredientIdRange(1, 21));
        var actual = a.mergeWith(b);

        Assertions.assertIterableEquals(expected.toList(), actual.toList());
    }
}