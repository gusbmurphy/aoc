package com.gusmurphy.fun.aoc.year2025.day6;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CephalopodMathParser {
    public static Stream<HomeworkProblem> fromFile(String absolutePath) {
        return LineReader.readAllLinesFrom(absolutePath)
                .collect(new HomeworkCollector());
    }

    private static class ThreeSectionColumn {
        List<Character> a, b, c;

        ThreeSectionColumn() {
            a = new ArrayList<>();
            b = new ArrayList<>();
            c = new ArrayList<>();
        }

        void addNextThree(Character x, Character y, Character z) {
            a.add(x);
            b.add(y);
            c.add(z);
        }
    }

    private static class HomeworkCollector implements Collector<String, List<ThreeSectionColumn>, Stream<HomeworkProblem>> {
        @Override
        public Supplier<List<ThreeSectionColumn>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<ThreeSectionColumn>, String> accumulator() {
            return (columns, string) ->
                    IntStream.iterate(0, i -> i < string.length(), i -> i + 4)
                            .forEach(columnStart -> {
                                int columnIndex = columnStart / 4;
                                if (columnIndex + 1 > columns.size()) {
                                    columns.add(new ThreeSectionColumn());
                                }
                                var charsInRow = IntStream.rangeClosed(columnStart, columnStart + 2)
                                        .mapToObj(i -> i < string.length() ? string.charAt(i) : ' ')
                                        .toList();
                                columns.get(columnIndex).addNextThree(
                                        charsInRow.get(0),
                                        charsInRow.get(1),
                                        charsInRow.get(2)
                                );
                            });
        }

        @Override
        public BinaryOperator<List<ThreeSectionColumn>> combiner() {
            return null;
        }

        @Override
        public Function<List<ThreeSectionColumn>, Stream<HomeworkProblem>> finisher() {
            return columns -> columns.stream()
                    .map(HomeworkCollector::fromColumn);
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }

        private static HomeworkProblem fromColumn(ThreeSectionColumn column) {
            var arguments = Stream.of(column.c, column.b, column.a)
                    .map(characters -> characters.stream()
                            .limit(column.a.size() - 1) // The last part of the column will be the operation...
                            .filter(c -> !c.equals(' '))
                            .map(String::valueOf)
                            .collect(Collectors.joining()))
                    .map(Long::valueOf)
                    .toList();

            var operation = column.a.getLast().equals('*') ? Operation.MULTIPLY : Operation.ADD;
            return new HomeworkProblem(operation, arguments);
        }
    }
}
