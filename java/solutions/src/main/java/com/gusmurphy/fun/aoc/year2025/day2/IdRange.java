package com.gusmurphy.fun.aoc.year2025.day2;

import module java.base;

public class IdRange {
    private final long start;
    private final long end;

    public IdRange(long start, long end) {
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
        IdRange idRange = (IdRange) o;
        return start == idRange.start && end == idRange.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
