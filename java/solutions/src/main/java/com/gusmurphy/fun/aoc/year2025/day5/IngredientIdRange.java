package com.gusmurphy.fun.aoc.year2025.day5;

import java.util.Objects;

public class IngredientIdRange {
    private final int lower;
    private final int upper;

    /**
     * An inclusive range.
     */
    public IngredientIdRange(int lower, int upper) {
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
}
