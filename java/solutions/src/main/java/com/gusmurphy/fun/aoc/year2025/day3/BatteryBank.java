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
        return allLengthNCombinationsOfBatteryJoltages(batteryJoltages, n, new ArrayList<>());
    }

    // Thank you very much to this article: https://hmkcode.com/calculate-find-all-possible-combinations-of-an-array-using-java/
    private static Stream<List<Integer>> allLengthNCombinationsOfBatteryJoltages(
            List<Integer> allJoltages, int n, List<Integer> workingCombo) {
        if (allJoltages.size() < n) {
            return Stream.empty();
        }

        if (n == 1) {
            return allJoltages.stream()
                    .map(joltage -> {
                        var newList = new ArrayList<>(workingCombo);
                        newList.add(joltage);
                        return newList;
                    });
        }

        if (allJoltages.size() == n) {
            workingCombo.addAll(allJoltages);
            return Stream.of(workingCombo);
        }

        return IntStream.range(0, allJoltages.size())
                .boxed()
                .parallel()
                .flatMap(i -> {
                    var newWorking = new ArrayList<>(workingCombo);
                    newWorking.add(allJoltages.get(i));
                    return allLengthNCombinationsOfBatteryJoltages(
                            allJoltages.subList(i + 1, allJoltages.size()),
                            n - 1,
                            newWorking
                    );
                });
    }
}
