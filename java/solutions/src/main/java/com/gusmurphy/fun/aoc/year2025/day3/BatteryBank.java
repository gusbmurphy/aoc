package com.gusmurphy.fun.aoc.year2025.day3;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class BatteryBank {
    private final List<Integer> batteryJoltages;

    public BatteryBank(String string) {
        batteryJoltages = string.codePoints()
                .mapToObj(c -> String.valueOf((char) c))
                .map(Integer::parseInt)
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BatteryBank that = (BatteryBank) o;
        return Objects.equals(batteryJoltages, that.batteryJoltages);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(batteryJoltages);
    }

    public int maxJoltageOfTwoBatteries() {
        return IntStream.range(0, batteryJoltages.size() - 1)
                .mapToObj(startIndex -> batteryJoltages.subList(startIndex, batteryJoltages.size()))
                .map(BatteryBank::maxJoltageFromPairInStreamStartingWithFirst)
                .max(Integer::compareTo)
                .orElseThrow(RuntimeException::new);
    }

    private static Integer maxJoltageFromPairInStreamStartingWithFirst(List<Integer> joltages) {
        var firstJoltage = joltages.getFirst().toString();

        return joltages.stream()
                .skip(1)
                .map(String::valueOf)
                .map(firstJoltage::concat)
                .map(Integer::parseInt)
                .max(Integer::compareTo)
                .orElseThrow(RuntimeException::new);
    }
}
