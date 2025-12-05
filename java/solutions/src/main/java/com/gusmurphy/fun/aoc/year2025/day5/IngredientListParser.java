package com.gusmurphy.fun.aoc.year2025.day5;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class IngredientListParser {
    public static IngredientList parseFile(String absolutePath) {
        return LineReader.readAllLinesFrom(absolutePath)
                .collect(new IngredientListCollector());
    }

    private static class IngredientListAccumulator {
        List<IngredientIdRange> ranges = new ArrayList<>();
        List<Long> freshIds = new ArrayList<>();
    }

    private static class IngredientListCollector implements Collector<String, IngredientListAccumulator, IngredientList> {
        private boolean hasSeenBreak = false;

        @Override
        public Supplier<IngredientListAccumulator> supplier() {
            return IngredientListAccumulator::new;
        }

        @Override
        public BiConsumer<IngredientListAccumulator, String> accumulator() {
            return (accumulator, string) -> {
                if (string.isBlank()) {
                    hasSeenBreak = true;
                    return;
                }

                if (hasSeenBreak) {
                    addFreshId(accumulator, string);
                } else {
                    addRange(accumulator, string);
                }
            };
        }

        private void addRange(IngredientListAccumulator accumulator, String string) {
            var bounds = string.split("-");
            var range = new IngredientIdRange(
                    Long.parseLong(bounds[0]),
                    Long.parseLong(bounds[1])
            );
            accumulator.ranges.add(range);
        }

        private void addFreshId(IngredientListAccumulator accumulator, String string) {
            var id = Long.parseLong(string);
            accumulator.freshIds.add(id);
        }

        @Override
        public BinaryOperator<IngredientListAccumulator> combiner() {
            return null;
        }

        @Override
        public Function<IngredientListAccumulator, IngredientList> finisher() {
            return accumulator -> new IngredientList(accumulator.ranges, accumulator.freshIds);
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }
}
