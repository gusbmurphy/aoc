package com.gusmurphy.fun.aoc.helper.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GridTest {
    
    @Test
    void simpleSquareGrid() {
        var grid = Grid.fromFile("src/test/resources/helper/simple-square.txt");
        
        var expectedRows = List.of(
                Line.of("abc"),
                Line.of("123"),
                Line.of("DEF")
        );

        Assertions.assertIterableEquals(expectedRows, grid.rows().toList());
    }
    
    @Test
    void unevenGrid() {
        var grid = Grid.fromFile("src/test/resources/helper/uneven-grid.txt");

        var expectedRows = List.of(
                Line.of("abc  "),
                Line.of("    d"),
                Line.of("...1 ")
        );

        Assertions.assertIterableEquals(expectedRows, grid.rows().toList());
    }
    
    @Test
    void columnsCanBeRetrieved() {
        var grid = Grid.fromFile("src/test/resources/helper/simple-square.txt");

        var expectedColumns = List.of(
                Line.of("a1D"),
                Line.of("b2E"),
                Line.of("c3F")
        );

        Assertions.assertIterableEquals(expectedColumns, grid.columns().toList());
    }
    
}
