package com.gusmurphy.fun.aoc.year2025.day4;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DepartmentFloor {
    private final List<String> rows;

    public DepartmentFloor(String... rows) {
        this.rows = Arrays.asList(rows);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentFloor that = (DepartmentFloor) o;
        return Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rows);
    }
}
