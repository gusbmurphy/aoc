package com.gusmurphy.fun.aoc.year2025.day1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DialTest {

    @Test
    void aDialStartsAt50() {
        var dial = new Dial();
        Assertions.assertEquals(50, dial.position());
    }

}
