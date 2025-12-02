package com.gusmurphy.fun.aoc.year2025.day2;

import module java.base;

public class ProductIdRange {
    private final long start;
    private final long end;

    public ProductIdRange(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public Stream<ProductId> stream() {
        return LongStream.range(start, end + 1)
                .mapToObj(ProductId::new);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductIdRange productIdRange = (ProductIdRange) o;
        return start == productIdRange.start && end == productIdRange.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
