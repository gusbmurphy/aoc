package com.gusmurphy.fun.aoc.year2025.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BatteryBankTest {

    @Test
    void largestJoltage() {
        var bank = new BatteryBank("11");
        assertEquals(11, bank.maxJoltageOfTwoBatteries());
    }

}
