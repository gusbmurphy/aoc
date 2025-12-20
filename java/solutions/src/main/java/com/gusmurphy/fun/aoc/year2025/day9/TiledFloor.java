package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.grid.Grid;
import com.gusmurphy.fun.aoc.helper.grid.GridDirection;
import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Stream;

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

        GridPosition.positionsBetweenExclusive(redTilePositionList.getLast(), redTilePositionList.getFirst())
                .forEach(greenPositions::add);

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

    public long sizeOfLargestRedCorneredRectangleInTiledArea() {
        return allRedCorneredRectangles()
                .filter(this::rectangleIsFullyWithinTiledArea)
                .mapToLong(Rectangle::area)
                .max().orElseThrow();
    }

    private Stream<Rectangle> allRedCorneredRectangles() {
        return redTilePositions.stream().flatMap(position -> redTilePositions.stream()
                .filter(other -> !other.equals(position))
                .map(other -> new Rectangle(position, other)));
    }

    private boolean rectangleIsFullyWithinTiledArea(Rectangle rectangle) {
        return rectangle.allPositions()
                .allMatch(this::positionIsInTiledArea);
    }

    private boolean positionIsInTiledArea(GridPosition position) {
        var intersectionsOnRay = numberOfBorderIntersectionsForHorizontalRayByYCoordinate(position.y());
        var numberOfIntersectionsAtX = intersectionsOnRay.get(position.x());
        return numberOfIntersectionsAtX % 2 != 0;
    }

    private final Map<Integer, Map<Integer, Integer>> intersectionRayMemo = new HashMap<>();

    private Map<Integer, Integer> numberOfBorderIntersectionsForHorizontalRayByYCoordinate(int y) {
        return intersectionRayMemo.computeIfAbsent(y, this::getIntersectionMapFor);
    }

    private Map<Integer, Integer> getIntersectionMapFor(int y) {
        var ray = grid.lineFromPositionHeading(new GridPosition(0, y), GridDirection.E);
        return ray.elements().collect(new RayIntersectionCollector());
    }

    private static class RayIntersectionTracker {
        private int lastIntersectionCount = 0;
        private boolean justSawColoredTile = false;
        private int nextY = 0;
        private final Map<Integer, Integer> numberOfIntersectionsByYCoordinate = new HashMap<>();

        public void trackNext(Tile tileAtPosition) {
            if (tileAtPosition.isColored()) {
                if (!justSawColoredTile) {
                    lastIntersectionCount++;
                    justSawColoredTile = true;
                }
            } else {
                justSawColoredTile = false;
            }

            numberOfIntersectionsByYCoordinate.put(nextY, lastIntersectionCount);
            nextY++;
        }

        public Map<Integer, Integer> numberOfIntersectionsByYCoordinate() {
            return numberOfIntersectionsByYCoordinate;
        }
    }

    private static class RayIntersectionCollector implements Collector<Tile, RayIntersectionTracker, Map<Integer, Integer>> {
        @Override
        public Supplier<RayIntersectionTracker> supplier() {
            return RayIntersectionTracker::new;
        }

        @Override
        public BiConsumer<RayIntersectionTracker, Tile> accumulator() {
            return RayIntersectionTracker::trackNext;
        }

        @Override
        public BinaryOperator<RayIntersectionTracker> combiner() {
            return null;
        }

        @Override
        public Function<RayIntersectionTracker, Map<Integer, Integer>> finisher() {
            return RayIntersectionTracker::numberOfIntersectionsByYCoordinate;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return Set.of();
        }
    }

    private enum Tile {
        RED, GREEN, BLANK;

        boolean isColored() {
            return this == RED || this == GREEN;
        }

        boolean isBlank() {
            return this == BLANK;
        }
    }
}
