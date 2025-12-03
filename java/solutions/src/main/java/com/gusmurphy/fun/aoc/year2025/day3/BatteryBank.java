package com.gusmurphy.fun.aoc.year2025.day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        return allLengthNCombinationsOfBatteryJoltages(numberOfBatteries)
                .map(list -> list.stream().map(String::valueOf).reduce("", String::concat))
                .map(Long::valueOf)
                .max(Long::compareTo)
                .orElseThrow();
    }

    private Stream<List<Integer>> allLengthNCombinationsOfBatteryJoltages(int n) {
        return createCombinations(batteryJoltages, new ArrayList<>(), 0, n);
    }

    // Thank you very much to Maarten Bodewes, I was very stuck!
    // https://stackoverflow.com/a/61031455
    private static Stream<List<Integer>> createCombinations(List<Integer> joltages,
                                                            List<Integer> currentCombination,
                                                            int index,
                                                            int missing) {
        if (missing == 0) {
            return Stream.of(currentCombination);
        }

        return IntStream.range(index, joltages.size() - missing + 1)
                .boxed()
                .flatMap(i -> {
                    List<Integer> newCombination;
                    if (i == joltages.size() - missing) {
                        newCombination = currentCombination;
                    } else {
                        newCombination = new ArrayList<>(currentCombination);
                    }
                    newCombination.add(joltages.get(i));
                    return createCombinations(joltages, newCombination, i + 1, missing - 1);
                });
    }
}
