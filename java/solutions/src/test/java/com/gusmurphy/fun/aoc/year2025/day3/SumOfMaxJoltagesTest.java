package com.gusmurphy.fun.aoc.year2025.day3;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumOfMaxJoltagesTest {

    @Test
    void exampleFromPuzzle() {
        var file = new File("src/test/resources/year2025/day3/example.txt");
        long result = SumOfMaxJoltages.forPairsInFile(file.getAbsolutePath());
        assertEquals(357, result);
    }

    @Test
    @Disabled
    void myInputForPart1() {
        var file = new File("src/main/resources/input/year2025/day3.txt");
        long result = SumOfMaxJoltages.forPairsInFile(file.getAbsolutePath());
        assertEquals(16887, result);
    }

    @Test
    void exampleFromPuzzlePart2() {
        var file = new File("src/test/resources/year2025/day3/example.txt");
        long result = SumOfMaxJoltages.for12BatteryCombosInFile(file.getAbsolutePath());
        assertEquals(3121910778619L, result);
    }

    @Test
    @Disabled
    void myInputForPart2() {
        var file = new File("src/main/resources/input/year2025/day3.txt");
        long result = SumOfMaxJoltages.for12BatteryCombosInFile(file.getAbsolutePath());
        assertEquals(167302518850275L, result);
    }

}
