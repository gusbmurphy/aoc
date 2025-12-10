package com.gusmurphy.fun.aoc.year2025.day7;

import com.gusmurphy.fun.aoc.helper.grid.Grid;
import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

import java.util.HashMap;
import java.util.List;

import static com.gusmurphy.fun.aoc.helper.grid.GridDirection.*;
import static com.gusmurphy.fun.aoc.year2025.day7.BeamConstants.*;

class PathCounter {
    private final HashMap<GridPosition, Long> memo = new HashMap<>();
    private final Grid<?> grid;
    private final List<GridPosition> splitters;
    private final GridPosition start;

    PathCounter(Grid<Character> grid) {
        this.start = grid.positionsOf(START).findFirst().orElseThrow();
        this.grid = grid;
        splitters = grid.positionsOf(SPLITTER).toList();
    }

    long countPaths() {
        return memoizedCountFrom(start);
    }

    private long memoizedCountFrom(GridPosition p) {
        if (memo.containsKey(p)) {
            return memo.get(p);
        }
        long result;

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
