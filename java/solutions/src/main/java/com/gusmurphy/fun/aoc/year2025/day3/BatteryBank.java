package com.gusmurphy.fun.aoc.year2025.day3;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
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

    public long maxJoltageOfTwoBatteries() {
        return maxJoltageOfNBatteries(2);
    }

    public long maxJoltageOfNBatteries(int numberOfBatteries) {
        return IntStream.iterate(numberOfBatteries, n -> n > 0, n -> n - 1)
                .boxed()
                .collect(new HighestJoltageCollector());
    }

    private class HighestJoltageCollector implements Collector<Integer, JoltageTracker, Long> {
        @Override
        public Supplier<JoltageTracker> supplier() {
            return JoltageTracker::new;
        }

        @Override
        public BiConsumer<JoltageTracker, Integer> accumulator() {
            return (tracker, joltagesLeftToFind) -> {
                var joltagesToSearch = tracker.toSearchNext;
                var indexOfHighestJoltage =
                        indexOfHighestJoltageAtLeastNAwayFromEnd(joltagesToSearch, joltagesLeftToFind);
                tracker.joltages.add(joltagesToSearch.get(indexOfHighestJoltage));
                tracker.toSearchNext = joltagesToSearch.subList(indexOfHighestJoltage + 1, joltagesToSearch.size());
            };
        }

        @Override
        public BinaryOperator<JoltageTracker> combiner() {
            return null;
        }

        @Override
        public Function<JoltageTracker, Long> finisher() {
            return joltageTracker -> totalJoltageOf(joltageTracker.joltages);
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }

    private class JoltageTracker {
        final List<Integer> joltages = new ArrayList<>();
        List<Integer> toSearchNext;

        JoltageTracker() {
            toSearchNext = batteryJoltages;
        }
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
