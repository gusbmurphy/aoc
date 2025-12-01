package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class DialRotationListParserTest {

    @Test
    void rotationListIsParsedCorrectly() {
        var file = new File("src/test/resources/year2025/day1/test-rotation-list.txt");
        List<DialRotation> result = DialRotationListParser.parseFile(file.getAbsolutePath());
        Assertions.assertNotNull(result);
    }

}
