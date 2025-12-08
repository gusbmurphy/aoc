package com.gusmurphy.fun.aoc.year2025.day7;

import com.gusmurphy.fun.aoc.helper.grid.Grid;
import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.helper.grid.GridDirection.*;

public class BeamSplitterSolver {
    private static final Character SPLITTER = '^';
    private static final char START = 'S';

    public static int countSplitsIn(String fileName) {
        var grid = Grid.fromFile(fileName);
        var startPosition = grid.positionsOf(START).findFirst().orElseThrow();
        var splitterPositions = grid.positionsOf(SPLITTER).toList();

        return Stream.iterate(
                        Optional.of(BeamProgression.startingAt(startPosition)),
                        Optional::isPresent,
                        bp -> bp.orElseThrow().nextWith(splitterPositions, grid))
                .mapToInt(bp -> bp.orElseThrow().splitCount)
                .sum();
    }

    public static int countPathsIn(String fileName) {
        var grid = Grid.fromFile(fileName);
        return new PathCounter(grid).countPaths();
    }

    private static class PathCounter {
        private final HashMap<GridPosition, Integer> memo = new HashMap<>();
        private final Grid<?> grid;
        private final List<GridPosition> splitters;
        private final GridPosition start;

        PathCounter(Grid<Character> grid) {
            this.start = grid.positionsOf(START).findFirst().orElseThrow();
            this.grid = grid;
            splitters = grid.positionsOf(SPLITTER).toList();
        }

        int countPaths() {
            return memoizedCountFrom(start);
        }

        private int memoizedCountFrom(GridPosition p) {
            if (memo.containsKey(p)) {
                return memo.get(p);
            }
            int result;

            if (!grid.containsPosition(p)) {
                result = 1;
            } else if (splitters.contains(p)) {
                result = memoizedCountFrom(p.toThe(W)) + memoizedCountFrom(p.toThe(E));
            } else {
                result = memoizedCountFrom(p.toThe(S));
            }

            memo.put(p, result);
            return result;
        }
    }

    private static Stream<GridPosition> nextPositionsFrom(GridPosition p, List<GridPosition> splitterPositions) {
        var positionToTheSouth = p.toThe(S);
        if (splitterPositions.contains(positionToTheSouth)) {
            return Stream.of(positionToTheSouth.toThe(W), positionToTheSouth.toThe(E));
        }
        return Stream.of(positionToTheSouth);
    }

    private static class BeamProgression {
        private final List<GridPosition> currentBeamHeads;
        private final int splitCount;

        private BeamProgression(List<GridPosition> currentBeamHeads, int splitCount) {
            this.currentBeamHeads = currentBeamHeads;
            this.splitCount = splitCount;
        }

        static BeamProgression startingAt(GridPosition position) {
            return new BeamProgression(List.of(position), 0);
        }

        Optional<BeamProgression> nextWith(List<GridPosition> splitterPositions, Grid<?> grid) {
            AtomicInteger newSplitCount = new AtomicInteger(0);
            var newBeamPositions = currentBeamHeads.stream()
                    .flatMap(beamHead -> {
                        var nextPositions = nextPositionsFrom(beamHead, splitterPositions).toList();
                        if (nextPositions.size() > 1) {
                            newSplitCount.incrementAndGet();
                        }
                        return nextPositions.stream();
                    })
                    .distinct()
                    .toList();

            if (newBeamPositions.stream().allMatch(grid::containsPosition)) {
                return Optional.of(new BeamProgression(newBeamPositions, newSplitCount.get()));
            }

            return Optional.empty();
        }
    }
}
