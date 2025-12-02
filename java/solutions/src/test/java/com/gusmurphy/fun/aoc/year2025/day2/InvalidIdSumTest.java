package com.gusmurphy.fun.aoc.year2025.day2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class InvalidIdSumTest {

    @Test
    void exampleFromPuzzleIsCorrect() {
        var file = new File("src/test/resources/year2025/day2/example.txt");
        long result = InvalidIdSum.forDoubleRepeatIdsInFile(file.getAbsolutePath());
        Assertions.assertEquals(1227775554, result);
    }

    @Test
    void myPuzzleInput() {
        var file = new File("src/main/resources/input/year2025/day2.txt");
        long result = InvalidIdSum.forDoubleRepeatIdsInFile(file.getAbsolutePath());
        Assertions.assertEquals(12850231731L, result);
    }

    @Test
    void exampleForPart2IsCorrect() {
        var file = new File("src/test/resources/year2025/day2/example.txt");
        long result = InvalidIdSum.forMultipleRepeatIdsInFile(file.getAbsolutePath());
        Assertions.assertEquals(4174379265L, result);
    }

}
