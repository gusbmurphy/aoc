package com.gusmurphy.fun.aoc.year2025.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.function.Function;

public class HomeworkSolverTest {

    @Test
    void part1Example() {
        long actual = solveFile(
                "src/test/resources/year2025/day6/example.txt",
                HomeworkSolver::sumOfAllAnswersInFile);
        Assertions.assertEquals(4277556L, actual);
    }

    @Test
    void part1MyInput() {
        long actual = solveFile(
                "src/main/resources/input/year2025/day6.txt",
                HomeworkSolver::sumOfAllAnswersInFile);
        Assertions.assertEquals(6371789547734L, actual);
    }

    @Test
    void part2Example() {
        long actual = solveFile(
                "src/test/resources/year2025/day6/example.txt",
                HomeworkSolver::sumOfAllAnswersInFileWithRealCephalopodFormat);
        Assertions.assertEquals(3263827L, actual);
    }

    @Test
    @Disabled("To be solved...")
    void part2MyInput() {
        long actual = solveFile(
                "src/main/resources/input/year2025/day6.txt",
                HomeworkSolver::sumOfAllAnswersInFileWithRealCephalopodFormat);
        Assertions.assertEquals(0, actual);
    }

    private long solveFile(String path, Function<String, Long> solver) {
        var file = new File(path);
        return solver.apply(file.getAbsolutePath());
    }

}
