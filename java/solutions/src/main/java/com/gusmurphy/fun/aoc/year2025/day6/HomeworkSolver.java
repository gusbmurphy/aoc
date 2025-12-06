package com.gusmurphy.fun.aoc.year2025.day6;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HomeworkSolver {
    public static long sumOfAllAnswersInFile(String absolutePath) {
        return LineReader.readAllLinesFrom(absolutePath)
                .collect(new HomeworkCollector())
                .map(HomeworkProblem::solve)
                .mapToLong(Long::valueOf)
                .sum();
    }

    private static class HomeworkAccumulator {
        List<List<Long>> arguments = new ArrayList<>();
        List<Operation> operations;
    }

    private static class HomeworkCollector implements Collector<String, HomeworkAccumulator, Stream<HomeworkProblem>> {
        @Override
        public Supplier<HomeworkAccumulator> supplier() {
            return HomeworkAccumulator::new;
        }

        @Override
        public BiConsumer<HomeworkAccumulator, String> accumulator() {
            return (accumulator, string) -> {
                var strings = Arrays.asList(string.split("\\s+"));

                try {
                    accumulator.arguments.add(
                            strings.stream()
                                    .filter(s -> !s.isBlank())
                                    .map(Long::parseLong)
                                    .toList());
                } catch (NumberFormatException e) {
                    accumulator.operations = strings.stream()
                            .map(s -> s.equals("*") ? Operation.MULTIPLY : Operation.ADD)
                            .toList();
                }
            };
        }

        @Override
        public BinaryOperator<HomeworkAccumulator> combiner() {
            return null;
        }

        @Override
        public Function<HomeworkAccumulator, Stream<HomeworkProblem>> finisher() {
            return accumulator ->
                    IntStream.range(0, accumulator.operations.size())
                            .mapToObj(i -> {
                                var argumentsForProblem = accumulator.arguments.stream().map(args -> args.get(i)).toList();
                                return new HomeworkProblem(accumulator.operations.get(i), argumentsForProblem);
                            });
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }
}
