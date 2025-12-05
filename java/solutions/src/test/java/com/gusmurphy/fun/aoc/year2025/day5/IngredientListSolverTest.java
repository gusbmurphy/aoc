package com.gusmurphy.fun.aoc.year2025.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

public class IngredientListSolverTest {

    @Test
    void part1Example() {
        var file = new File("src/test/resources/year2025/day5/example.txt");
        long actual = IngredientListSolver.countFreshIngredientsInFile(file.getAbsolutePath());
        Assertions.assertEquals(3, actual);
    }

    @Test
    void part1MyInput() {
        var file = new File("src/main/resources/input/year2025/day5.txt");
        long actual = IngredientListSolver.countFreshIngredientsInFile(file.getAbsolutePath());
        Assertions.assertEquals(601, actual);
    }

    @Test
    void part2Example() {
        var file = new File("src/test/resources/year2025/day5/example.txt");
        long actual = IngredientListSolver.countPossibleFreshIds(file.getAbsolutePath());
        Assertions.assertEquals(14, actual);
    }

}
