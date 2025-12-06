package com.gusmurphy.fun.aoc.year2025.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class HomeworkSolverTest {

    @Test
    void part1Example() {
        var file = new File("src/test/resources/year2025/day6/example.txt");
        long actual = HomeworkSolver.sumOfAllAnswersInFile(file.getAbsolutePath());
        Assertions.assertEquals(4277556L, actual);
    }

}
