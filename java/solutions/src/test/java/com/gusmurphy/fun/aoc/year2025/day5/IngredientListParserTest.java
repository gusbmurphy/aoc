package com.gusmurphy.fun.aoc.year2025.day5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class IngredientListParserTest {

    @Test
    void exampleIsParsedCorrectly() {
        var file = new File("src/test/resources/year2025/day5/test-example.txt");
        IngredientList actual = IngredientListParser.parseFile(file.getAbsolutePath());

        var expected = new IngredientList(
                List.of(
                        new JaggedIdRange.IdRange(3L, 5L),
                        new JaggedIdRange.IdRange(10L, 14L)
                ),
                List.of(1L, 5L, 8L)
        );

        Assertions.assertEquals(expected, actual);
    }

}
