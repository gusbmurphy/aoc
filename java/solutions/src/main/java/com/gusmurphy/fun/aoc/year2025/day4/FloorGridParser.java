package com.gusmurphy.fun.aoc.year2025.day4;

import com.gusmurphy.fun.aoc.helper.LineReader;

public class FloorGridParser {
    public static DepartmentFloor parseFile(String absolutePath) {
        var lines = LineReader.readAllLinesFrom(absolutePath).toList();
        return new DepartmentFloor(lines.toArray(new String[0]));
    }
}
