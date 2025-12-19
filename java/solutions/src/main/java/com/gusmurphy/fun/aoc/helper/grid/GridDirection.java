package com.gusmurphy.fun.aoc.helper.grid;

public enum GridDirection {
    N, NE, E, SE, S, SW, W, NW;

    public static GridDirection fromAToB(GridPosition a, GridPosition b) {
        if (a.equals(b)) {
            throw new IllegalArgumentException("Positions must be different");
        }

        var xDifference = b.x() - a.x();
        var yDifference = b.y() - a.y();

        if (xDifference == 0) {
            if (yDifference > 0) {
                return S;
            }

            return N;
        }

        if (yDifference == 0) {
            if (xDifference > 0) {
                return E;
            }

            return W;
        }

        if (Math.abs(xDifference) != Math.abs(yDifference)) {
            throw new IllegalArgumentException("Positions must have a straight line of coordinates between them");
        }

        if (yDifference > 0) {
            if (xDifference > 0) {
                return SE;
            }
            return SW;
        }

        if (xDifference > 0) {
            return NE;
        }

        return NW;
    }
}
