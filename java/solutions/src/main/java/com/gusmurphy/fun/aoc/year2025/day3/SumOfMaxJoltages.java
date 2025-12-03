package com.gusmurphy.fun.aoc.year2025.day3;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    static void main() {
        printLineWithDateAndThread("Solving for part 2...");
        var file = new File("java/solutions/src/main/resources/input/year2025/day3.txt");
        var result = BatteryBankParser.parseFile(file.getAbsolutePath())
                .parallel()
                .map(bank -> {
                    printLineWithDateAndThread(String.format("Finding max voltage for bank %s...", bank));
                    var maxVoltage = bank.maxJoltageOfNBatteries(12);
                    printLineWithDateAndThread(String.format("Max voltage for bank %s: %s", bank, maxVoltage));
                    return maxVoltage;
                })
                .reduce(Long::sum)
                .orElseThrow();
        printLineWithDateAndThread(String.format("(%s) Solution for part 2: %s", LocalDateTime.now(), result));
    }

    private static void printLineWithDateAndThread(String s) {
        var time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        var thread = Thread.currentThread().getName();
        System.out.printf("%s::%s %s%n", time, thread, s);
    }
}
