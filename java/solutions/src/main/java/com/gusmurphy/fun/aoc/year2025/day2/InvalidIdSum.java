package com.gusmurphy.fun.aoc.year2025.day2;

import java.util.function.Predicate;

public class InvalidIdSum {
    private InvalidIdSum() {
    }

    public static long forDoubleRepeatIdsInFile(String absolutePath) {
        return getSumOfIdsMatchingPredicateInFile(absolutePath, ProductId::justTwiceRepeatedSequence);
    }

    public static long forMultipleRepeatIdsInFile(String absolutePath) {
        return getSumOfIdsMatchingPredicateInFile(absolutePath, ProductId::justRepeatedSequenceAnyNumberOfTimes);
    }

    private static long getSumOfIdsMatchingPredicateInFile(String absolutePath, Predicate<ProductId> predicate) {
        return IdRangeListParser.parseFile(absolutePath)
                .flatMap(ProductIdRange::stream)
                .filter(predicate)
                .map(ProductId::value)
                .reduce(0L, Long::sum);
    }
}
