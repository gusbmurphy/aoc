package com.gusmurphy.fun.aoc.year2025.day3;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public long maxJoltageOfNBatteries(int numberOfBatteries) {
        var indexesOfHighestJoltageCombination = new ArrayList<Integer>();
        int indexOfStartOfSubListToSearch = 0;
        int numberOfIndexesToFind = numberOfBatteries;
        while (indexesOfHighestJoltageCombination.size() < numberOfBatteries) {
            var subListToSearch = batteryJoltages.subList(indexOfStartOfSubListToSearch, batteryJoltages.size());
            int highestIndex = indexOfHighestJoltageAtLeastNAwayFromEnd(subListToSearch, numberOfIndexesToFind) + indexOfStartOfSubListToSearch;

            indexesOfHighestJoltageCombination.add(highestIndex);
            indexOfStartOfSubListToSearch = highestIndex + 1;
            numberOfIndexesToFind--;
        }

        var highestJoltageCombo = indexesOfHighestJoltageCombination.stream().map(batteryJoltages::get).toList();
        return totalJoltageOf(highestJoltageCombo);
    }

    private static int indexOfHighestJoltageAtLeastNAwayFromEnd(List<Integer> joltages, int n) {
        return IntStream.range(0, joltages.size() - n + 1)
                        .boxed()
                        .collect(Collectors.toMap(Function.identity(), joltages::get))
                        .entrySet()
                        .stream()
                        .sorted(Comparator.comparingInt(Map.Entry::getKey))
                        .max(Comparator.comparingInt(Map.Entry::getValue))
                        .orElseThrow()
                        .getKey();
    }

    private static long totalJoltageOf(List<Integer> joltages) {
        var string = joltages.stream().map(String::valueOf).reduce("", String::concat);
        return Long.parseLong(string);
    }
}
