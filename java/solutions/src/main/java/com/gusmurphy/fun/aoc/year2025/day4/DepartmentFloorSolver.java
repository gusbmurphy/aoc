package com.gusmurphy.fun.aoc.year2025.day4;

public class DepartmentFloorSolver {
    public static int accessibleRollCountFromFile(String absolutePath) {
        var floor = FloorGridParser.parseFile(absolutePath);
        return floor.accessibleRollCount();
    }
}
