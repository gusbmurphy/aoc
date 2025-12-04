package com.gusmurphy.fun.aoc.year2025.day4;

import java.util.stream.Stream;

public class DepartmentFloorSolver {
    public static long accessibleRollCountFromFile(String absolutePath) {
        var floor = FloorGridParser.parseFile(absolutePath);
        return floor.accessibleRollCount();
    }

    public static long removableRollCountFromFile(String absolutePath) {
        var initialFloor = FloorGridParser.parseFile(absolutePath);

        return Stream.iterate(initialFloor,
                        floor -> floor.accessibleRollCount() > 0,
                        DepartmentFloor::removeAllAccessibleRolls)
                .mapToLong(DepartmentFloor::accessibleRollCount)
                .sum();
    }
}
