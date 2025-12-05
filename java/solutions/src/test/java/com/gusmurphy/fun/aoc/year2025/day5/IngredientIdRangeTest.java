package com.gusmurphy.fun.aoc.year2025.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

class IngredientIdRangeTest {

    @Test
    void twoNonOverLappingRangesCombinedStayTheSameWhenMerged() {
        var a = new IngredientIdRange(1, 4);
        var b = new IngredientIdRange(6, 8);

        var expected = Stream.of(
                new IngredientIdRange(1, 4),
                new IngredientIdRange(6, 8)
        );
        Stream<IngredientIdRange> actual = a.mergeWith(b);

        Assertions.assertIterableEquals(expected.toList(), actual.toList());
    }

}