package com.gusmurphy.fun.aoc.year2025.day8;

public record ThreeDimensionalPoint(int x, int y, int z) {
    public double distanceTo(ThreeDimensionalPoint other) {
        return Math.sqrt(
                Math.pow((x - other.x), 2) +
                Math.pow((y - other.y), 2) +
                Math.pow((z - other.z), 2)
        );
    }
}
