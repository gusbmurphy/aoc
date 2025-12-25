package com.gusmurphy.fun.aoc.year2024.day1;

import java.util.List;

public class SimilarityScore {
    public static long ofListsInFile(String filePath) {
        var locationLists = LocationListParser.parseFile(filePath);

        return locationLists.left().stream()
                .mapToLong(number -> number * countOccurrencesInList(number, locationLists.right()))
                .sum();
    }

    private static long countOccurrencesInList(int target, List<Integer> list) {
        return list.stream().filter(other -> other == target).count();
    }
}
