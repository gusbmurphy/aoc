package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static com.gusmurphy.fun.aoc.year2025.day1.DialRotation.Direction.*;

public class DialRotationListParserTest {

    @Test
    void rotationListIsParsedCorrectly() {
        var file = new File("src/test/resources/year2025/day1/test-rotation-list.txt");
        List<DialRotation> result = DialRotationListParser.parseFile(file.getAbsolutePath());

        var expected = List.of(
                new DialRotation(LEFT, 68),
                new DialRotation(LEFT, 30),
                new DialRotation(RIGHT, 48),
                new DialRotation(LEFT, 5)
        );

        Assertions.assertIterableEquals(expected, result);
    }

}
