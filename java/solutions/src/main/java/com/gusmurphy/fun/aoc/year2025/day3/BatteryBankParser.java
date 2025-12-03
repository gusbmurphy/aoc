package com.gusmurphy.fun.aoc.year2025.day3;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.util.stream.Stream;

public class BatteryBankParser {
    public static Stream<BatteryBank> parseFile(String absolutePath) {
        return LineReader.readAllLinesFrom(absolutePath)
                .map(BatteryBank::new);
    }
}
