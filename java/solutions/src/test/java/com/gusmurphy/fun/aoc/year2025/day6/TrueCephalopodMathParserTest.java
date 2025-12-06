package com.gusmurphy.fun.aoc.year2025.day6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static com.gusmurphy.fun.aoc.year2025.day6.Operation.ADD;
import static com.gusmurphy.fun.aoc.year2025.day6.Operation.MULTIPLY;

public class TrueCephalopodMathParserTest {

    @Test
    void homeworkIsCorrectlyParsedForTrueCephalopodStyle() {
        var file = new File("src/test/resources/year2025/day6/test-sample.txt");
        var actual = CephalopodMathParser.fromFile(file.getAbsolutePath());

        var expected = List.of(
                new HomeworkProblem(MULTIPLY, List.of(356L, 24L, 1L)),
                new HomeworkProblem(ADD, List.of(8L, 248L, 369L))
        );

        Assertions.assertEquals(expected, actual.toList());
    }

    @Test
    void fileWithWeirderLayoutIsParsedCorrectly() {
        var file = new File("src/test/resources/year2025/day6/weird-sample.txt");
        var actual = CephalopodMathParser.fromFile(file.getAbsolutePath());

        var expected = List.of(
                new HomeworkProblem(MULTIPLY, List.of(5L, 31L, 7L))
        );

        Assertions.assertEquals(expected, actual.toList());
    }

}
