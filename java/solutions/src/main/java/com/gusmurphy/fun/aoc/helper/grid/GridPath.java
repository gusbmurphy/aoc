package com.gusmurphy.fun.aoc.helper.grid;

import java.util.List;
import java.util.stream.Stream;

public class GridPath {
    private final List<GridPosition> positions;
    
    private GridPath(List<GridPosition> positions) {
        this.positions = positions;
    }
    
    public GridPath continueWith(GridPosition position) {
        return new GridPath(Stream.concat(positions.stream(), Stream.of(position)).toList());
    }
    
    public GridPath concatWith(GridPath other) {
        return new GridPath(Stream.concat(positions.stream(), other.positions.stream()).toList());
    }
}
