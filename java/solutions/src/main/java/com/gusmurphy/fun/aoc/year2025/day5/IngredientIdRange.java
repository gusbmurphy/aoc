package com.gusmurphy.fun.aoc.year2025.day5;

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

    public IngredientIdRange(Long... stops) {
        rangeList = Stream.iterate(0, i -> i < stops.length - 1, i -> i + 1)
                .map(i -> new SingleRange(stops[i], stops[i + 1]))
                .filter(r -> r.lower != r.upper)
                .toList();
    }

    public LongStream allIds() {
        return LongStream.rangeClosed(rangeList.getFirst().lower, rangeList.getFirst().upper);
    }

    public boolean includes(long id) {
        return id >= rangeList.getFirst().lower && id <= rangeList.getFirst().upper;
    }

    public IngredientIdRange mergeWith(IngredientIdRange other) {
        var allStops = Stream.concat(this.rangeList.stream(), other.rangeList.stream())
                .flatMap(r -> Stream.of(r.lower, r.upper))
                .sorted();

        var result = allStops.toArray(Long[]::new);
        return new IngredientIdRange(result);
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
