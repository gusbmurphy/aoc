package com.gusmurphy.fun.aoc.helper.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class GridTest {
    
    @Test
    void simpleSquareGrid() {
        var grid = Grid.fromFile("src/test/resources/helper/simple-square.txt");
        
        var expectedRows = List.of(
                StringLine.of("abc"),
                StringLine.of("123"),
                StringLine.of("DEF")
        );

        Assertions.assertIterableEquals(expectedRows, grid.rows().toList());
    }
    
    @Test
    void unevenGrid() {
        var grid = Grid.fromFile("src/test/resources/helper/uneven-grid.txt");

        var expectedRows = List.of(
                StringLine.of("abc  "),
                StringLine.of("    d"),
                StringLine.of("...1 ")
        );

        Assertions.assertIterableEquals(expectedRows, grid.rows().toList());
    }
    
    @Test
    void columnsCanBeRetrieved() {
        var grid = Grid.fromFile("src/test/resources/helper/simple-square.txt");

        var expectedColumns = List.of(
                StringLine.of("a1D"),
                StringLine.of("b2E"),
                StringLine.of("c3F")
        );

        Assertions.assertIterableEquals(expectedColumns, grid.columns().toList());
    }
    
    @ParameterizedTest
    @MethodSource
    void positionsCanBeCheckedToSeeIfTheyAreOnGrid(int x, int y, boolean expected) {
        var grid = Grid.fromFile("src/test/resources/helper/simple-square.txt");
        var position = new GridPosition(x, y);
        Assertions.assertEquals(expected, grid.containsPosition(position));
    }
    
    private static Stream<Arguments> positionsCanBeCheckedToSeeIfTheyAreOnGrid() {
        return Stream.of(
                Arguments.of(1, 2, true),
                Arguments.of(0, 0, true),
                Arguments.of(0, 0, true),
                Arguments.of(3, 0, false),
                Arguments.of(0, 3, false),
                Arguments.of(3, 3, false)
        );
    }
    
}
