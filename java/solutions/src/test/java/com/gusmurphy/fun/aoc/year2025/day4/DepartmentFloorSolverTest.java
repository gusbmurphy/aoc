package com.gusmurphy.fun.aoc.year2025.day4;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartmentFloorSolverTest {

    @Test
    void part1Example() {
        var file = new File("src/test/resources/year2025/day4/example-grid.txt");
        long result = DepartmentFloorSolver.accessibleRollCountFromFile(file.getAbsolutePath());
        assertEquals(13, result);
    }

    @Test
    void part1MyInput() {
        var file = new File("src/main/resources/input/year2025/day4.txt");
        long result = DepartmentFloorSolver.accessibleRollCountFromFile(file.getAbsolutePath());
        assertEquals(1395, result);
    }

}
