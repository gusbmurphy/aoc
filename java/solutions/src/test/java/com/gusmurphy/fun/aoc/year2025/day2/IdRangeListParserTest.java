package com.gusmurphy.fun.aoc.year2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import module java.base;

public class IdRangeListParserTest {

    @Test
    void rangesAreParsedFromFile() {
        var file = new File("src/test/resources/year2025/day2/test-id-range.txt");
        List<IdRange> result = IdRangeListParser.parseFile(file.getAbsolutePath());
        Assertions.assertNotNull(result);
    }

}
