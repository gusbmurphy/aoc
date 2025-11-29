package com.gusmurphy.fun.aoc.year2024.day1;

import java.util.stream.IntStream;

public class TotalDistance {

    public static int from(LocationListPair listPair) {
        var sortedLeft = listPair.left().stream().sorted().toList();
        var sortedRight = listPair.right().stream().sorted().toList();

        return IntStream.range(0, sortedLeft.size())
                .map(i -> Math.abs(sortedLeft.get(i) - sortedRight.get(i)))
                .sum();
    }

}
