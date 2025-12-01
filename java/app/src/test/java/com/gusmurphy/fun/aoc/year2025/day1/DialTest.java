package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.year2025.day1.DialRotation.Direction.*;

public class DialTest {

    @Test
    void aDialStartsAt50() {
        var dial = new Dial();
        Assertions.assertEquals(50, dial.position());
    }

    @ParameterizedTest
    @MethodSource("rotationsAndExpectedPositions")
    void rotatingTheDialReturnsANewOneAtTheNewPosition(DialRotation rotation, int expectedPosition) {
        var start = new Dial();
        Dial result = start.rotate(rotation);
        Assertions.assertEquals(expectedPosition, result.position());
    }

    private static Stream<Arguments> rotationsAndExpectedPositions() {
        return Stream.of(
                Arguments.of(new DialRotation(RIGHT, 5), 55)
        );
    }

}
