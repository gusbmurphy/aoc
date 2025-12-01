package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DialRotationTest {

    @Test
    void aRotationCanBeBrokenIntoSingleStepIncrements() {
        var rotation = new DialRotation(DialRotation.Direction.LEFT, 3);
        Assertions.assertEquals(3, rotation.steps().toList().size());
        Assertions.assertTrue(rotation.steps().allMatch(step -> step.direction() == DialRotation.Direction.LEFT));
    }

}
