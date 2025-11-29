package com.gusmurphy.fun.aoc.year2024.day1;

import java.util.List;

public class LocationListPair {
    private final List<Integer> left;
    private final List<Integer> right;

    public LocationListPair(List<Integer> left, List<Integer> right) {
        this.left = left;
        this.right = right;
    }

    public List<Integer> left() {
        return left;
    }

    public List<Integer> right() {
        return right;
    }
}
