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

    public int accessibleRollCount() {
        int floorHeight = rows.size();
        int floorWidth = rows.getFirst().size();
        int count = 0;

        for (int y = 0; y < floorHeight; y++) {
            for (int x = 0; x < floorWidth; x++) {
                if (rows.get(y).get(x) == false) {
                    continue;
                }

                var position = new GridPosition(x, y);
                var occupiedSurroundingPositionCount = position.surroundingPositions()
                        .filter(p -> p.x() < floorWidth && p.y() < floorHeight)
                        .map(p -> rows.get(p.y()).get(p.x()))
                        .filter(Boolean::booleanValue)
                        .count();

                if (occupiedSurroundingPositionCount < 4) {
                    count++;
                }
            }
        }

        return count;
    }

    record GridPosition(int x, int y) {
        Stream<GridPosition> surroundingPositions() {
            return IntStream.rangeClosed(x - 1, x + 1)
                    .filter(otherX -> otherX >= 0)
                    .boxed()
                    .flatMap(otherX ->
                            IntStream.rangeClosed(y - 1, y + 1)
                                    .filter(otherY -> otherY >= 0)
                                    .boxed()
                                    .map(otherY -> new GridPosition(otherX, otherY))
                    )
                    .filter(position -> !position.equals(this));
        }
    }
}
