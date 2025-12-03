package com.gusmurphy.fun.aoc.year2025.day3;

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


}
