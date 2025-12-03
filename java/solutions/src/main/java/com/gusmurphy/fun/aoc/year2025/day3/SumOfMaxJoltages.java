package com.gusmurphy.fun.aoc.year2025.day3;

public class SumOfMaxJoltages {
    public static long forPairsInFile(String absolutePath) {
        return BatteryBankParser.parseFile(absolutePath)
                .map(BatteryBank::maxJoltageOfTwoBatteries)
                .reduce(Integer::sum)
                .map(Long::valueOf)
                .orElseThrow();
    }

    public static long for12BatteryCombosInFile(String absolutePath) {
        return BatteryBankParser.parseFile(absolutePath)
                .parallel()
                .map(bank -> bank.maxJoltageOfNBatteries(12))
                .reduce(Long::sum)
                .orElseThrow();
    }
}
