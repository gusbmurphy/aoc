package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class IngredientIdRange {
    private final long lower;
    private final long upper;

    /**
     * An inclusive range.
     */
    public IngredientIdRange(long lower, long upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public LongStream allIds() {
        return LongStream.rangeClosed(lower, upper);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        IngredientIdRange that = (IngredientIdRange) o;
        return lower == that.lower && upper == that.upper;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lower, upper);
    }

    public boolean includes(long id) {
        return id >= lower && id <= upper;
    }

    public Stream<IngredientIdRange> mergeWith(IngredientIdRange other) {
        var sorted = Stream.of(this, other).sorted(Comparator.comparingLong(r -> r.lower)).toList();
        var startingRange = sorted.getFirst();
        var endingRange = sorted.getLast();

        if (startingRange.upper + 1 == endingRange.lower) {
            return Stream.of(new IngredientIdRange(startingRange.lower, endingRange.upper));
        }

        return Stream.of(
                startingRange,
                endingRange
        );
    }
}
