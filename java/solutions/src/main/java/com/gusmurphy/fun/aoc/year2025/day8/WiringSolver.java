package com.gusmurphy.fun.aoc.year2025.day8;

import java.util.Comparator;

public class WiringSolver {
    public static long productOfThreeLargestCircuits(String path, int maxNumberOfConnections) {
        var points = ThreeDimensionalPointListParser.parseFile(path).toList();
        var circuitCollection = new CircuitCollection(points);
        points.stream()
                .flatMap(p -> points.stream()
                        .filter(o -> !o.equals(p))
                        .map(o -> new PointPair(p, o)))
                .distinct()
                .sorted(Comparator.comparing(PointPair::distanceBetween, Double::compareTo))
                .limit(maxNumberOfConnections)
                .forEach(circuitCollection::createConnectionFor);
        
        return circuitCollection.circuitsBySizeDescending()
                .map(s -> Long.valueOf(s.size()))
                .sorted((a, b) -> Long.compare(b, a))
                .limit(3)
                .reduce(1L, (a, b) -> a * b);
    }
}
