package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class DialRotationTest {

    @ParameterizedTest
    @MethodSource("rotations")
    void aRotationCanBeBrokenIntoSingleStepIncrements(DialRotation rotation) {
        Assertions.assertEquals(rotation.distance(), rotation.steps().toList().size());
        Assertions.assertTrue(rotation.steps().allMatch(step -> step.direction() == rotation.direction()));
    }

    private static Stream<Arguments> rotations() {
        return Stream.of(
                Arguments.of(new DialRotation(DialRotation.Direction.LEFT, 8)),
                Arguments.of(new DialRotation(DialRotation.Direction.RIGHT, 10234))
        );
    }

}
