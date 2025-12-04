package com.gusmurphy.fun.aoc.year2025.day4;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DepartmentFloor {
    /**
     * 2-dimensional list where true means this spot is occupied.
     */
    private final List<List<Boolean>> rows;

    public DepartmentFloor(String... rows) {
        this.rows = Arrays.stream(rows)
                .map(rowString -> rowString.codePoints()
                        .mapToObj(c -> (char) c)
                        .map(c -> c == '@')
                        .toList()
                )
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentFloor that = (DepartmentFloor) o;
        return Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rows);
    }

    public long accessibleRollCount() {
        return allPositions()
                .filter(this::positionIsOccupied)
                .map(position -> position.surroundingPositions()
                        .filter(this::positionIsOnGrid)
                        .filter(this::positionIsOccupied)
                        .count())
                .filter(surroundingOccupiedCount -> surroundingOccupiedCount < 4)
                .count();
    }

    private boolean positionIsOnGrid(GridPosition position) {
        return position.x >= 0
                && position.x < gridWidth()
                && position.y >= 0
                && position.y < gridHeight();
    }

    private boolean positionIsOccupied(GridPosition position) {
        return rows.get(position.y).get(position.x);
    }

    private Stream<GridPosition> allPositions() {
        int gridWidth = rows.getFirst().size();
        int gridHeight = rows.size();

        return IntStream.range(0, gridWidth)
                .boxed()
                .flatMap(x -> IntStream.range(0, gridHeight)
                        .mapToObj(y -> new GridPosition(x, y)));
    }

    private int gridWidth() {
        return rows.getFirst().size();
    }

    private int gridHeight() {
        return rows.size();
    }

    record GridPosition(int x, int y) {
        Stream<GridPosition> surroundingPositions() {
            return IntStream.rangeClosed(x - 1, x + 1)
                    .boxed()
                    .flatMap(otherX ->
                            IntStream.rangeClosed(y - 1, y + 1)
                                    .boxed()
                                    .map(otherY -> new GridPosition(otherX, otherY))
                    )
                    .filter(position -> !position.equals(this));
        }
    }
}
