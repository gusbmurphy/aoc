package com.gusmurphy.fun.aoc.helper.grid;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GridTest {
    
    @Test
    void simpleSquareGrid() {
        var grid = Grid.fromFile("src/test/resources/helper/simple-square.txt");
        
        var expectedRows = List.of(
                Row.of("abc"),
                Row.of("123"),
                Row.of("DEF")
        );

        Assertions.assertIterableEquals(expectedRows, grid.rows().toList());
    }
    
    @Test
    void unevenGrid() {
        var grid = Grid.fromFile("src/test/resources/helper/uneven-grid.txt");

        var expectedRows = List.of(
                Row.of("abc  "),
                Row.of("    d"),
                Row.of("...1 ")
        );

        Assertions.assertIterableEquals(expectedRows, grid.rows().toList());
    }
    
}
