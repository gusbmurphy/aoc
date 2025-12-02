package com.gusmurphy.fun.aoc.year2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

}
