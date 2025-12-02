package com.gusmurphy.fun.aoc.year2025.day2;

import java.util.Objects;

public class ProductId {
    private final String value;

    public ProductId(long id) {
        this.value = Long.toString(id);
    }

    public long value() {
        return Long.parseLong(value);
    }

    public boolean justTwiceRepeatedSequence() {
        int valueLength = value.length();
        if (valueLength % 2 != 0) {
            return false;
        }

        var secondHalfStartingIndex = valueLength / 2;
        var firstHalf = value.substring(0, secondHalfStartingIndex);
        var secondHalf = value.substring(secondHalfStartingIndex);

        return firstHalf.equals(secondHalf);
    }

    @Override
    public String toString() {
        return "ProductId{" +
                "value='" + value + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return Objects.equals(value, productId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
