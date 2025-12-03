package com.gusmurphy.fun.aoc.year2025.day3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatteryBankTest {

    @ParameterizedTest
    @MethodSource
    void largestJoltage(String bankString, int expectedJoltage) {
        var bank = new BatteryBank(bankString);
        assertEquals(expectedJoltage, bank.maxJoltageOfTwoBatteries());
    }

    static Stream<Arguments> largestJoltage() {
        return Stream.of(
                Arguments.of("11", 11),
                Arguments.of("321", 32),
                Arguments.of("811111111111119", 89)
        );
    }

}
