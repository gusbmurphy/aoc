package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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

    public LongStream allIds() {
        return LongStream.rangeClosed(rangeList.getFirst().lower, rangeList.getFirst().upper);
    }

    public boolean includes(long id) {
        return id >= rangeList.getFirst().lower && id <= rangeList.getFirst().upper;
    }

    public Stream<IngredientIdRange> mergeWith(IngredientIdRange other) {
        var sorted = Stream.of(this, other).sorted(Comparator.comparingLong(r -> r.rangeList.getFirst().lower)).toList();
        var startingRange = sorted.getFirst();
        var endingRange = sorted.getLast();

        if (startingRange.rangeList.getFirst().upper + 1 == endingRange.rangeList.getFirst().lower) {
            return Stream.of(new IngredientIdRange(startingRange.rangeList.getFirst().lower, endingRange.rangeList.getFirst().upper));
        }

        if (startingRange.rangeList.getFirst().lower <= endingRange.rangeList.getFirst().lower) {
            if (endingRange.rangeList.getFirst().lower > startingRange.rangeList.getFirst().upper) {
                return Stream.of(
                        startingRange,
                        endingRange
                );
            }
            if (startingRange.rangeList.getFirst().upper >= endingRange.rangeList.getFirst().upper) {
                return Stream.of(startingRange);
            }
        }

        return Stream.of(new IngredientIdRange(startingRange.rangeList.getFirst().lower, endingRange.rangeList.getFirst().upper));
    }

    private record SingleRange(long lower, long upper) {
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
