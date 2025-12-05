package com.gusmurphy.fun.aoc.year2025.day4;

import org.junit.jupiter.api.Disabled;
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

    @Test
    void part2Example() {
        var file = new File("src/test/resources/year2025/day4/example-grid.txt");
        long result = DepartmentFloorSolver.removableRollCountFromFile(file.getAbsolutePath());
        assertEquals(43, result);
    }

    @Test
    @Disabled("This one takes a second, worth revisiting this to figure out why!")
    void part2MyInput() {
        var file = new File("src/main/resources/input/year2025/day4.txt");
        long result = DepartmentFloorSolver.removableRollCountFromFile(file.getAbsolutePath());
        assertEquals(8451, result);
    }

}
