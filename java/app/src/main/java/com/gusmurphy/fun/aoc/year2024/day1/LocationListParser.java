package com.gusmurphy.fun.aoc.year2024.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class LocationListParser {
    public static LocationListPair parseFile(String filePath) {
        var left = new ArrayList<Integer>();
        var right = new ArrayList<Integer>();

        try (var reader = new BufferedReader(new FileReader(filePath))) {
            reader.readAllLines().stream()
                    .map(LocationListParser::parseLine)
                    .forEach(array -> {
                        left.add(array[0]);
                        right.add(array[1]);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
