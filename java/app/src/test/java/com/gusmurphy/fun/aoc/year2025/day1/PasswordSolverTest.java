package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordSolverTest {

    @Test
    void exampleFromPuzzle() {
        int solution = PasswordSolver.solveFromFile("src/test/resources/year2025/day1/rotation-list-example.txt");
        Assertions.assertEquals(3, solution);
    }

}
