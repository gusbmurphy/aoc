package com.gusmurphy.fun.aoc.year2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class InvalidIdSumTest {

    @Test
    void exampleFromPuzzleIsCorrect() {
        var file = new File("src/test/resources/year2025/day2/example.txt");
        long result = InvalidIdSum.ofFile(file.getAbsolutePath());
        Assertions.assertEquals(1227775554, result);
    }

}
