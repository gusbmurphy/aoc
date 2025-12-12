package com.gusmurphy.fun.aoc.year2025.day8;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CircuitCollectionTest {

    private static final ThreeDimensionalPoint A = new ThreeDimensionalPoint(1, 2, 3);
    private static final ThreeDimensionalPoint B = new ThreeDimensionalPoint(2, 2, 3);
    private static final ThreeDimensionalPoint C = new ThreeDimensionalPoint(3, 2, 3);
    
    private final CircuitCollection circuits = new CircuitCollection(List.of(A, B, C));

    @Test
    void allPointsStartOnTheirOwnCircuits() {
        var expected = List.of(
                Set.of(A),
                Set.of(B),
                Set.of(C)
        );
        var actual = circuits.circuitsBySizeDescending().toList();
        assertIterableEquals(expected, actual);
    }
    
    @Test
    void creatingConnectionForAPairBringsThemToTheSameSet() {
        circuits.createConnectionFor(new PointPair(A, B));
        
        var expected = List.of(
                Set.of(A, B),
                Set.of(C)
        );
        var actual = circuits.circuitsBySizeDescending().toList();
        assertIterableEquals(expected, actual);
    }
    
    @Test
    void creatingConnectionForAPairWithACircuitedPointAddsToThatCircuit() {
        circuits.createConnectionFor(new PointPair(A, B));
        circuits.createConnectionFor(new PointPair(C, B));

        var expected = List.of(
                Set.of(A, B, C)
        );
        var actual = circuits.circuitsBySizeDescending().toList();
        assertIterableEquals(expected, actual);
    }

}