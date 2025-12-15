package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

import java.util.Set;

public class TiledFloor {
    private final Set<GridPosition> redTilePositions;

    private TiledFloor(Set<GridPosition> redTilePositions) {
        this.redTilePositions = redTilePositions;
    }

    public static TiledFloor withRedTilesAtPositions(Set<GridPosition> positions) {
        return new TiledFloor(positions);
    }


    public boolean positionIsWithinTiles(GridPosition position) {
        return redTilePositions.contains(position);
    }
}
