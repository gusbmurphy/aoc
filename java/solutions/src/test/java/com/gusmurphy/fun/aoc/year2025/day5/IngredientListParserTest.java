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
                        new IngredientIdRange(3, 5),
                        new IngredientIdRange(10, 14)
                ),
                List.of(1L, 5L, 8L)
        );

        Assertions.assertEquals(expected, actual);
    }

}
