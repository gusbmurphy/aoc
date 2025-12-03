package com.gusmurphy.fun.aoc.year2025.day3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatteryBankTest {

    @ParameterizedTest
    @MethodSource
    void largestJoltageOfTwoBatteries(String bankString, int expectedJoltage) {
        var bank = new BatteryBank(bankString);
        assertEquals(expectedJoltage, bank.maxJoltageOfTwoBatteries());
    }

    static Stream<Arguments> largestJoltageOfTwoBatteries() {
        return Stream.of(
                Arguments.of("11", 11),
                Arguments.of("321", 32),
                Arguments.of("811111111111119", 89),
                Arguments.of("234234234234278", 78),
                Arguments.of("818181911112111", 92)
        );
    }

    @ParameterizedTest
    @MethodSource
    void largestJoltageOfNBatteries(String bankString, int numberOfBatteries, int expectedJoltage) {
        var bank = new BatteryBank(bankString);
        assertEquals(expectedJoltage, bank.maxJoltageOfNBatteries(numberOfBatteries));
    }

    static Stream<Arguments> largestJoltageOfNBatteries() {
        return Stream.of(
                Arguments.of("321", 3, 321),
                Arguments.of("32155", 3, 355)
        );
    }

}
