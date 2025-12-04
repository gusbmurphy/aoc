package com.gusmurphy.fun.aoc.year2025.day4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DepartmentFloorTest {

    @Test
    void accessibleRollCount() {
        var floor = new DepartmentFloor(
                "@.",
                ".."
        );

        Assertions.assertEquals(1, floor.accessibleRollCount());
    }

}
