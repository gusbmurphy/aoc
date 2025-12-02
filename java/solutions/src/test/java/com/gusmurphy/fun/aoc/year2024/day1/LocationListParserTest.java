package com.gusmurphy.fun.aoc.year2024.day1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LocationListParserTest {

    @Test
    void listIsParsedCorrectly() {
        var file = new File("src/test/resources/year2024/day1/test-list.txt");
        LocationListPair result = LocationListParser.parseFile(file.getAbsolutePath());

        var expectedLeft = List.of(3, 4, 2, 1, 3, 3);
        var expectedRight = List.of(4, 3, 5, 3, 9, 3);

        assertIterableEquals(expectedLeft, result.left());
        assertIterableEquals(expectedRight, result.right());
    }

}
