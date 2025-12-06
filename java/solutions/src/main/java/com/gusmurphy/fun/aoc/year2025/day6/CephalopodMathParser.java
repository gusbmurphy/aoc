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
        var allLines = LineReader.readAllLinesFrom(absolutePath).toList();
        var columnBounds = columnBoundsFrom(allLines);

        return allLines.stream().collect(new HomeworkCollector(columnBounds));
    }

    private static List<ColumnBounds> columnBoundsFrom(List<String> allLinesInFile) {
        int lineLength = allLinesInFile.getFirst().length();
        var allColumnStarts = IntStream.range(0, lineLength)
                .filter(i -> allLinesInFile.stream().allMatch(line -> line.charAt(i) == ' '))
                .map(i -> i + 1)
                .boxed().toList();
        
        var allColumnSeparators = new ArrayList<>(allColumnStarts);
        allColumnSeparators.addFirst(0);
        allColumnSeparators.addLast(lineLength + 1);

        return IntStream.range(1, allColumnSeparators.size())
                .boxed()
                .map(i -> new ColumnBounds(allColumnSeparators.get(i - 1), allColumnSeparators.get(i) - 1))
                .toList();
    }

    private static class ColumnGroup {
        List<ArrayList<Character>> columnList;

        ColumnGroup(int width) {
            columnList = Stream.generate(() -> new ArrayList<Character>()).limit(width).toList();
        }

        void addRow(List<Character> characters) {
            if (characters.size() != columnList.size()) {
                throw new IllegalArgumentException("Cannot add row of different size");
            }

            IntStream.range(0, columnList.size())
                    .forEach(i -> columnList.get(i).add(characters.get(i)));
        }
    }

    private record ColumnBounds(int start, int end) {
        ColumnBounds {
            if (start >= end) {
                throw new IllegalArgumentException("Start must be less than end");
            }
        }

        int size() {
            return end - start;
        }
    }

    private static class HomeworkCollector implements Collector<String, List<ColumnGroup>, Stream<HomeworkProblem>> {
        private final List<ColumnBounds> columnBounds;

        HomeworkCollector(List<ColumnBounds> columnBounds) {
            this.columnBounds = columnBounds;
        }

        @Override
        public Supplier<List<ColumnGroup>> supplier() {
            return ArrayList::new;
        }

        @Override
        public BiConsumer<List<ColumnGroup>, String> accumulator() {
            return (columns, string) ->
                    IntStream.range(0, columnBounds.size())
                            .forEach(i -> {
                                var bounds = columnBounds.get(i);
                                if (i + 1 > columns.size()) {
                                    columns.add(new ColumnGroup(bounds.size()));
                                }
                                var charsInRow = IntStream.range(bounds.start, bounds.end)
                                        .mapToObj(j -> j < string.length() ? string.charAt(j) : ' ')
                                        .toList();
                                columns.get(i).addRow(charsInRow);
                            });
        }

        @Override
        public BinaryOperator<List<ColumnGroup>> combiner() {
            return null;
        }

        @Override
        public Function<List<ColumnGroup>, Stream<HomeworkProblem>> finisher() {
            return columns -> columns.stream()
                    .map(HomeworkCollector::fromColumn);
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }

        private static HomeworkProblem fromColumn(ColumnGroup column) {
            var arguments = column.columnList.reversed().stream()
                    .map(characters -> characters.stream()
                            .filter(Character::isDigit)
                            .map(String::valueOf)
                            .collect(Collectors.joining()))
                    .map(Long::valueOf)
                    .toList();

            var operation = column.columnList.getFirst().getLast().equals('*') ? Operation.MULTIPLY : Operation.ADD;
            return new HomeworkProblem(operation, arguments);
        }
    }
}
