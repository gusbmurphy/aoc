package com.gusmurphy.fun.aoc.year2025.day9;

import com.gusmurphy.fun.aoc.helper.grid.GridPosition;

class Rectangle {
    private final GridPosition c1;
    private final GridPosition c2;
    
    public Rectangle(GridPosition c1, GridPosition c2) {
        this.c1 = c1;
        this.c2 = c2;
    }
    
    public long area() {
        long width = Math.abs(c1.x() - c2.x()) + 1;
        long height = Math.abs(c1.y() - c2.y()) + 1;
        return width * height;
    }
}
