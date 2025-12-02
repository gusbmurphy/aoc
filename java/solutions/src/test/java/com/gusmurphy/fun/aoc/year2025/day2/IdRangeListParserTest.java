package com.gusmurphy.fun.aoc.year2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import module java.base;

public class IdRangeListParserTest {

    @Test
    void rangesAreParsedFromFile() {
        var file = new File("src/test/resources/year2025/day2/test-id-range.txt");
        List<IdRange> result = IdRangeListParser.parseFile(file.getAbsolutePath()).toList();

        var expected = List.of(
                new IdRange(11, 22),
                new IdRange(95, 115),
                new IdRange(998, 1012)
        );

        Assertions.assertIterableEquals(expected, result);
    }

}
