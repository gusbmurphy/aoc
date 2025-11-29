package com.gusmurphy.fun.aoc.year24.day1;

import com.gusmurphy.fun.aoc.year2024.day1.LocationListPair;
import com.gusmurphy.fun.aoc.year2024.day1.TotalDistance;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TotalDistanceTest {

    @Test
    void totalDistanceFromExampleIsCorrect() {
        var listPair = new LocationListPair(
                List.of(3, 4, 2, 1, 3, 3),
                List.of(4, 3, 5, 3, 9, 3)
        );

        int result = TotalDistance.from(listPair);
        Assertions.assertEquals(11, result);
    }

}
