package com.gusmurphy.fun.aoc.year2025.day1;

import com.gusmurphy.fun.aoc.helper.Reader;

import java.util.List;
import java.util.regex.Pattern;

public class DialRotationListParser {
    public static List<DialRotation> parseFile(String absolutePath) {
        return Reader.readAllLinesFrom(absolutePath)
                .map(DialRotationListParser::parseLine)
                .toList();
    }

    private static final Pattern DIRECTION_PATTERN = Pattern.compile("[RL]");
    private static final Pattern DISTANCE_PATTERN = Pattern.compile("\\d+");
    private static DialRotation parseLine(String line) {
        var directionMatcher = DIRECTION_PATTERN.matcher(line);
        directionMatcher.find();
        var directionString = directionMatcher.group(0);
        var direction = switch (directionString) {
            case "R" -> DialRotation.Direction.RIGHT;
            case "L" -> DialRotation.Direction.LEFT;
            default -> throw new IllegalStateException("Unexpected direction: " + directionString);
        };

        var distanceMatcher = DISTANCE_PATTERN.matcher(line);
        distanceMatcher.find();
        var distance = Integer.parseInt(distanceMatcher.group(0));

        return new DialRotation(direction, distance);
    }
}
