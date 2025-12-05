package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class IngredientIdRange {
    private final List<SingleRange> rangeList;

    /**
     * An inclusive range.
     */
    public IngredientIdRange(long lower, long upper) {
        rangeList = List.of(new SingleRange(lower, upper));
    }

    public IngredientIdRange(Long... stops) {
        rangeList = Stream.iterate(0, i -> i < stops.length - 1, i -> i + 2)
                .map(i -> new SingleRange(stops[i], stops[i + 1]))
                .filter(r -> r.lower != r.upper)
                .toList();
    }

    private IngredientIdRange(List<SingleRange> rangeList) {
        this.rangeList = rangeList;
    }

    public LongStream allIds() {
        return LongStream.rangeClosed(rangeList.getFirst().lower, rangeList.getFirst().upper);
    }

    public boolean includes(long id) {
        return id >= rangeList.getFirst().lower && id <= rangeList.getFirst().upper;
    }

    public IngredientIdRange mergeWith(IngredientIdRange other) {
        var allRanges = Stream.concat(this.rangeList.stream(), other.rangeList.stream());

        var mergedRanges = allRanges
                .sorted(Comparator.comparingLong(SingleRange::lower))
                .collect(new RangeListReducer());

        return new IngredientIdRange(mergedRanges);
    }

    private record SingleRange(long lower, long upper) {
        public SingleRange {
            if (lower > upper) {
                throw new IllegalArgumentException("Upper must be greater than lower");
            }
        }

        Stream<SingleRange> union(SingleRange other) {
            var sortedRanges = Stream.of(this, other).sorted(Comparator.comparingLong(a -> a.lower)).toList();
            var first = sortedRanges.getFirst();
            var second = sortedRanges.getLast();

            if (first.upper >= second.lower) {
                return Stream.of(new SingleRange(first.lower, second.upper));
            }

            return sortedRanges.stream();
        }
    }

    private static class RangeListReducer implements Collector<SingleRange, Stack<SingleRange>, List<SingleRange>> {
        @Override
        public Supplier<Stack<SingleRange>> supplier() {
            return Stack::new;
        }

        @Override
        public BiConsumer<Stack<SingleRange>, SingleRange> accumulator() {
            return (stack, next) -> {
                if (stack.isEmpty()) {
                    stack.push(next);
                    return;
                }

                var last = stack.pop();
                last.union(next).forEach(stack::push);
            };
        }

        @Override
        public BinaryOperator<Stack<SingleRange>> combiner() {
            return null;
        }

        @Override
        public Function<Stack<SingleRange>, List<SingleRange>> finisher() {
            return stack -> stack.stream().toList();
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IngredientIdRange that = (IngredientIdRange) o;
        return Objects.equals(rangeList, that.rangeList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rangeList);
    }
}
