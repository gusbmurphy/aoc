package com.gusmurphy.fun.aoc.year2025.day1;

public record DialRotation(Direction direction, int distance) {
    public enum Direction {
        LEFT, RIGHT
    }
}
