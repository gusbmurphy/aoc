package com.gusmurphy.fun.aoc.year2025.day2;

public class InvalidIdSum {
    private InvalidIdSum() {
    }

    public static long ofFile(String absolutePath) {
        return IdRangeListParser.parseFile(absolutePath)
                .flatMap(ProductIdRange::stream)
                .filter(ProductId::justTwiceRepeatedSequence)
                .map(ProductId::value)
                .reduce(0L, Long::sum);
    }
}
