package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordSolverTest {

    @Test
    void exampleFromPuzzle() {
        int solution = PasswordSolver.solveFromFile("src/test/resources/year2025/day1/rotation-list-example.txt");
        Assertions.assertEquals(3, solution);
    }

    @Test
    void exampleFromPuzzleForPart2() {
        int solution = PasswordSolver.solveFromFileForEveryOccurrenceOfZero("src/test/resources/year2025/day1/rotation-list-example.txt");
        Assertions.assertEquals(6, solution);
    }

    @Test
    void solutionFromMyInput() {
        int solution = PasswordSolver.solveFromFile("src/main/resources/input/year2025/day1.txt");
        Assertions.assertEquals(1081, solution);
    }

}
