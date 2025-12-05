package com.gusmurphy.fun.aoc.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Stream;

public class LineReader {
    private LineReader() {
    }

    public static Stream<String> readAllLinesFrom(String absolutePath) {
        try (var reader = new BufferedReader(new java.io.FileReader(absolutePath))) {
            return reader.lines().toList().stream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
