package com.gusmurphy.fun.aoc.year2025.day1;

import java.util.stream.Stream;

public class PasswordSolver {
    public static long solveFromFile(String absolutePathToFile) {
        var rotationList = DialRotationListParser.parseFile(absolutePathToFile);
        var rotationsIterator = rotationList.iterator();

        return Stream.iterate(new Dial(), dial -> dial.rotate(rotationsIterator.next()))
                .limit(rotationList.size())
                .filter(dial -> dial.position() == 0)
                .count();
    }

    public static long solveFromFileForEveryOccurrenceOfZero(String absolutePathToFile) {
        var rotationList = DialRotationListParser.parseFile(absolutePathToFile);
        var allStepsList = rotationList.stream().flatMap(DialRotation::steps).toList();
        var stepsIterator = allStepsList.iterator();

        return Stream.iterate(new Dial(), dial -> dial.rotate(stepsIterator.next()))
                .limit(allStepsList.size())
                .filter(dial -> dial.position() == 0)
                .count();
    }
}
