package com.gusmurphy.fun.aoc.year2025.day2;

import java.util.stream.Stream;

public class ProductId {
    private final String value;

    public ProductId(long id) {
        this.value = Long.toString(id);
    }

    public boolean containsTwiceRepeatedSequence() {
        return Stream.iterate(value, (s) -> s.substring(1))
                .limit(value.length() - 1)
                .anyMatch(ProductId::stringBeginsWithDuplicatedSequence);
    }

    private static boolean stringBeginsWithDuplicatedSequence(String segment) {
        var firstCharacter = segment.charAt(0);
        var indexOfNextOccurrence = segment.substring(1).indexOf(firstCharacter) + 1;

        if (indexOfNextOccurrence == 0) {
            return false;
        }

        var firstSequence = segment.substring(0, indexOfNextOccurrence);
        var sequenceFromSecondOccurrence = segment.substring(indexOfNextOccurrence);
        return sequenceFromSecondOccurrence.startsWith(firstSequence);
    }

    @Override
    public String toString() {
        return "ProductId{" +
                "value='" + value + '\'' +
                '}';
    }
}
