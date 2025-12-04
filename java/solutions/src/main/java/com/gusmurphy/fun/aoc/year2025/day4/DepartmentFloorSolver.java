package com.gusmurphy.fun.aoc.year2025.day4;

public class DepartmentFloorSolver {
    public static long accessibleRollCountFromFile(String absolutePath) {
        var floor = FloorGridParser.parseFile(absolutePath);
        return floor.accessibleRollCount();
    }

    // TODO: Not the best here!
    public static long removableRollCountFromFile(String absolutePath) {
        var floor = FloorGridParser.parseFile(absolutePath);

        long removableRollCount = 0;
        while (floor.accessibleRollCount() > 0) {
            removableRollCount += floor.accessibleRollCount();
            floor = floor.removeAllAccessibleRolls();
        }
        return removableRollCount;
    }
}
