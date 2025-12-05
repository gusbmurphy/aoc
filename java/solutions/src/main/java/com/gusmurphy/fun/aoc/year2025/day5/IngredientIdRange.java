package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.Objects;

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
}
