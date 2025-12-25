package com.gusmurphy.fun.aoc;

public class ResourcePath {
    private ResourcePath() {
    }

    public static String toTestResource(int year, int day, String name) {
        return String.format("src/test/resources/year%s/day%s/%s.txt", year, day, name);
    }

    @Deprecated
    public static String toTestResource(int day, String name) {
        return toTestResource(2025, day, name);
    }
    
    public static String toRealInput(int day) {
        return String.format("src/main/resources/input/year2025/day%s.txt", day);
    }

}
