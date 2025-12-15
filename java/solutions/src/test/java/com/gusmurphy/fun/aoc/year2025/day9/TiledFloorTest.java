package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.grid.GridPosition;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TiledFloorTest {

    private static final TiledFloor FLOOR = TiledFloor.withRedTilesAtPositions(
            Set.of(
                    new GridPosition(5, 5),
                    new GridPosition(5, 10),
                    new GridPosition(10, 10),
                    new GridPosition(10, 7),
                    new GridPosition(7, 7),
                    new GridPosition(7, 5)
            )
    );

    @ParameterizedTest
    @MethodSource
    void weCanCheckIfAPositionIsWithinTheTiledSpace(GridPosition position, boolean expected) {
        assertEquals(expected, FLOOR.positionIsWithinTiles(position));
    }

    private static Stream<Arguments> weCanCheckIfAPositionIsWithinTheTiledSpace() {
        return Stream.of(
                Arguments.of(new GridPosition(5, 5), true)
        );
    }

}