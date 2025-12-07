package com.gusmurphy.fun.aoc.helper.grid;

public record GridPosition(int x, int y) {
    public GridPosition toThe(GridDirection direction) {
        return switch (direction) {
            case N -> new GridPosition(this.x, this.y - 1);
            case NE -> new GridPosition(this.x + 1, this.y - 1);
            case E -> new GridPosition(this.x + 1, this.y);
            case SE -> new GridPosition(this.x + 1, this.y + 1);
            case S -> new GridPosition(this.x, this.y + 1);
            case SW -> new GridPosition(this.x - 1, this.y + 1);
            case W -> new GridPosition(this.x - 1, this.y);
            case NW -> new GridPosition(this.x - 1, this.y - 1);
        };
    }
}
