package com.gusmurphy.fun.aoc.year2025.day8;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.util.Arrays;
import java.util.stream.Stream;

public class ThreeDimensionalCoordinatesParser {
    public static Stream<ThreeDimensionalCoordinates> parseFile(String filePath) {
        return LineReader.readAllLinesFrom(filePath)
                .map(s -> Arrays.stream(s.split(",")).toList())
                .map(strings -> strings.stream().map(Integer::parseInt).toList())
                .map(coordinates -> new ThreeDimensionalCoordinates(coordinates.get(0), coordinates.get(1), coordinates.get(2)));
    }
}
