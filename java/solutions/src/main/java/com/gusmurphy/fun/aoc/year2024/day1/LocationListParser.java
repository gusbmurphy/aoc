package com.gusmurphy.fun.aoc.year2024.day1;

import com.gusmurphy.fun.aoc.helper.Reader;

import java.util.ArrayList;
import java.util.Arrays;

public class LocationListParser {
    public static LocationListPair parseFile(String filePath) {
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();

        Reader.readAllLinesFrom(filePath)
                .map(LocationListParser::parseLine)
                .forEach(array -> {
                    left.add(array[0]);
                    right.add(array[1]);
                });

        return new LocationListPair(left, right);
    }

    private static int[] parseLine(String line) {
        var matches = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).toList();
        return new int[]{
                matches.getFirst(),
                matches.getLast()
        };
    }
}
