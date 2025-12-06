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

    @Test
    void part1MyInput() {
        var file = new File("src/main/resources/input/year2025/day6.txt");
        long actual = HomeworkSolver.sumOfAllAnswersInFile(file.getAbsolutePath());
        Assertions.assertEquals(6371789547734L, actual);
    }

    @Test
    void part2Example() {
        var file = new File("src/test/resources/year2025/day6/example.txt");
        long actual = HomeworkSolver.sumOfAllAnswersInFileWithRealCephalopodFormat(file.getAbsolutePath());
        Assertions.assertEquals(3263827L, actual);
    }

}
