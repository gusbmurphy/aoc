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
                List.of('a', 'b', 'c'),
                List.of('1', '2', '3'),
                List.of('D', 'E', 'F')
        );
        var actual = streamOfLinesToListOfLists(grid.rows());

        Assertions.assertIterableEquals(expectedRows, actual);
    }

    @Test
    void unevenGrid() {
        var grid = Grid.fromFile("src/test/resources/helper/uneven-grid.txt");

        var expectedRows = List.of(
                List.of('a', 'b', 'c', ' ', ' '),
                List.of(' ', ' ', ' ', ' ', 'd'),
                List.of('.', '.', '.', '1', ' ')
        );
        var actual = streamOfLinesToListOfLists(grid.rows());

        Assertions.assertIterableEquals(expectedRows, actual);
    }

    @Test
    void columnsCanBeRetrieved() {
        var grid = Grid.fromFile("src/test/resources/helper/simple-square.txt");

        var expectedColumns = List.of(
                List.of('a', '1', 'D'),
                List.of('b', '2', 'E'),
                List.of('c', '3', 'F')
        );
        var actual = streamOfLinesToListOfLists(grid.columns());

        Assertions.assertIterableEquals(expectedColumns, actual);
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

    private static <T> List<List<T>> streamOfLinesToListOfLists(Stream<Line<T>> streamOfLines) {
        return streamOfLines.map(line -> line.elements().toList()).toList();
    }
}
