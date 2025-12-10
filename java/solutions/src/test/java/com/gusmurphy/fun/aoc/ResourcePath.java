package com.gusmurphy.fun.aoc;

public class ResourcePath {
    private ResourcePath() {
    }

    public static String toTestResource(int day, String name) {
        return String.format("src/test/resources/year2025/day%s/%s.txt", day, name);
    }
    
    public static String toRealInput(int day) {
        return String.format("src/main/resources/input/year2025/day%s.txt", day);
    }

}
