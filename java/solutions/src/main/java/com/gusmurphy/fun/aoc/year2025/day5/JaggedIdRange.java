package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class JaggedIdRange {
    private final List<IdRange> rangeList;

    /**
     * An inclusive range.
     */
    public JaggedIdRange(long lower, long upper) {
        rangeList = List.of(new IdRange(lower, upper));
    }

    public JaggedIdRange(IdRange range) {
        rangeList = List.of(range);
    }

    public JaggedIdRange(Long... stops) {
        rangeList = Stream.iterate(0, i -> i < stops.length - 1, i -> i + 2)
                .map(i -> new IdRange(stops[i], stops[i + 1]))
                .filter(r -> r.lower != r.upper)
                .toList();
    }

    public long rangeLength() {
        return rangeList.stream()
                .mapToLong(IdRange::length)
                .sum();
    }

    private JaggedIdRange(List<IdRange> rangeList) {
        this.rangeList = rangeList;
    }

    public boolean includes(long id) {
        return rangeList.stream().anyMatch(range -> range.includes(id));
    }

    public JaggedIdRange mergeWith(JaggedIdRange other) {
        var allRanges = Stream.concat(this.rangeList.stream(), other.rangeList.stream());

        var mergedRanges = allRanges
                .sorted(Comparator.comparingLong(IdRange::lower))
                .collect(new RangeListReducer());

        return new JaggedIdRange(mergedRanges);
    }

    @Override
    public String toString() {
        return "JaggedIdRange{" +
                "rangeList=" + rangeList +
                '}';
    }

    public record IdRange(long lower, long upper) {
        public IdRange {
            if (lower > upper) {
                throw new IllegalArgumentException("Upper must be greater than lower");
            }
        }

        long length() {
            return upper - lower + 1;
        }

        boolean includes(long l) {
            return lower <= l && upper >= l;
        }

        Stream<IdRange> union(IdRange other) {
            var sortedRanges = Stream.of(this, other).sorted(Comparator.comparingLong(a -> a.lower)).toList();
            var first = sortedRanges.getFirst();
            var second = sortedRanges.getLast();

            if (first.upper >= second.lower) {
                var higherUpper = Long.max(first.upper, second.upper);
                return Stream.of(new IdRange(first.lower, higherUpper));
            }

            return sortedRanges.stream();
        }

        @Override
        public String toString() {
            return "IdRange{" +
                    "lower=" + lower +
                    ", upper=" + upper +
                    '}';
        }
    }

    private static class RangeListReducer implements Collector<IdRange, Stack<IdRange>, List<IdRange>> {
        @Override
        public Supplier<Stack<IdRange>> supplier() {
            return Stack::new;
        }

        @Override
        public BiConsumer<Stack<IdRange>, IdRange> accumulator() {
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
        public BinaryOperator<Stack<IdRange>> combiner() {
            return null;
        }

        @Override
        public Function<Stack<IdRange>, List<IdRange>> finisher() {
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
        JaggedIdRange that = (JaggedIdRange) o;
        return Objects.equals(rangeList, that.rangeList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rangeList);
    }
}
