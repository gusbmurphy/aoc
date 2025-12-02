package com.gusmurphy.fun.aoc.year2025.day2;

import module java.base;
import com.gusmurphy.fun.aoc.helper.LineReader;

public class IdRangeListParser {
    private IdRangeListParser() {
    }

    public static Stream<ProductIdRange> parseFile(String absolutePath) {
        return LineReader.readAllLinesFrom(absolutePath)
                .limit(1)
                .flatMap(line -> Arrays.stream(line.split(",")))
                .map(string -> {
                    var split = string.split("-");
                    return new ProductIdRange(Long.parseLong(split[0]), Long.parseLong(split[1]));
                });
    }
}
