package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.grid.Grid;
import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

import java.util.*;

public class TiledFloor {
    private final Grid<Tile> grid;
    private final Set<GridPosition> redTilePositions;

    private TiledFloor(List<GridPosition> redTilePositionList) {
        var greenPositions = new HashSet<GridPosition>();

        for (int i = 0, j = 1; j < redTilePositionList.size(); i++, j++) {
            var lineStart = redTilePositionList.get(i);
            var lineEnd = redTilePositionList.get(j);
            GridPosition.positionsBetweenExclusive(lineStart, lineEnd)
                    .forEach(greenPositions::add);
        }

        var redTilePositionSet = new HashSet<>(redTilePositionList);
        this.redTilePositions = redTilePositionSet;
        Map<Tile, Set<GridPosition>> tileColorPositions = Map.of(
                Tile.RED, redTilePositionSet, Tile.GREEN, greenPositions);

        var width = redTilePositionList.stream().mapToInt(GridPosition::x).max().orElseThrow() + 2;
        var height = redTilePositionList.stream().mapToInt(GridPosition::y).max().orElseThrow() + 2;

        grid = Grid.fromPositions(tileColorPositions, Tile.BLANK, width, height);
    }

    public static TiledFloor withRedTilesAtPositions(List<GridPosition> positions) {
        return new TiledFloor(positions);
    }


    public boolean positionIsWithinTiles(GridPosition position) {
        return redTilePositions.contains(position);
    }

    private enum Tile {
        RED, GREEN, BLANK
    }
}
