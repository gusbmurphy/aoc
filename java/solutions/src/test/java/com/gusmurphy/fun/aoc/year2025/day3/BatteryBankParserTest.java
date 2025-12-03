package com.gusmurphy.fun.aoc.year2025.day3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Stream;

public class BatteryBankParserTest {

    @Test
    void banksAreParsedFromFile() {
        var file = new File("src/test/resources/year2025/day3/test-banks.txt");
        Stream<BatteryBank> result = BatteryBankParser.parseFile(file.getAbsolutePath());

        var expected = List.of(
                new BatteryBank("643"),
                new BatteryBank("987"),
                new BatteryBank("123")
        );

        Assertions.assertIterableEquals(expected, result.toList());
    }

}
