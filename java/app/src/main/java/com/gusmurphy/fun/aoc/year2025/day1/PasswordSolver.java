package com.gusmurphy.fun.aoc.year2025.day1;

public class PasswordSolver {
    public static int solveFromFile(String absolutePathToFile) {
        var rotationList = DialRotationListParser.parseFile(absolutePathToFile);
        int zeroCount = 0;
        var dial = new Dial();

        for (var r : rotationList) {
            dial = dial.rotate(r);
            if (dial.position() == 0) {
                zeroCount++;
            }
        }

        return zeroCount;
    }
}
