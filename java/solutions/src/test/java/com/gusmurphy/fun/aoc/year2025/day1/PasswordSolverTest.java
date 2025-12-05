package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class PasswordSolverTest {

    @Test
    void exampleFromPuzzle() {
        var solution = PasswordSolver.solveFromFile("src/test/resources/year2025/day1/rotation-list-example.txt");
        Assertions.assertEquals(3, solution);
    }

    @Test
    void exampleFromPuzzleForPart2() {
        var solution = PasswordSolver.solveFromFileForEveryOccurrenceOfZero("src/test/resources/year2025/day1/rotation-list-example.txt");
        Assertions.assertEquals(6, solution);
    }

    @Test
    @Disabled
    void solutionFromMyInput() {
        var solution = PasswordSolver.solveFromFile("src/main/resources/input/year2025/day1.txt");
        Assertions.assertEquals(1081, solution);
    }

    @Test
    @Disabled
    void solutionFromMyInputForPart2() {
        var solution = PasswordSolver.solveFromFileForEveryOccurrenceOfZero("src/main/resources/input/year2025/day1.txt");
        Assertions.assertEquals(6689, solution);
    }

}
