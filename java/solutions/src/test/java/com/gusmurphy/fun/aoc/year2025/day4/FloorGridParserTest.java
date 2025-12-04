package com.gusmurphy.fun.aoc.year2025.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FloorGridParserTest {

    @Test
    void floorGridIsParsedCorrectly() {
        var file = new File("src/test/resources/year2025/day4/test-grid.txt");
        DepartmentFloor result = FloorGridParser.parseFile(file.getAbsolutePath());

        var expected = new DepartmentFloor(
                "..@@",
                "@@@.",
                "@@@@"
        );

        Assertions.assertEquals(expected, result);
    }

}
