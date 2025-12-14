package com.gusmurphy.fun.aoc.year2025.day8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CircuitCollection {
    private final Map<ThreeDimensionalPoint, ThreeDimensionalPoint> parentsByChildren;
    private final Set<ThreeDimensionalPoint> points;

    CircuitCollection(List<ThreeDimensionalPoint> points) {
        parentsByChildren = points.stream()
                .collect(Collectors.toMap(p -> p, p -> p));
        this.points = new HashSet<>(points);
    }

    void createConnectionFor(PointPair pair) {
        var newParent = parentsByChildren.get(pair.a());
        var oldParent = parentsByChildren.get(pair.b());

        reparentWithDescendants(oldParent, newParent);
    }

    private void reparentWithDescendants(ThreeDimensionalPoint oldParent, ThreeDimensionalPoint newParent) {
        parentsByChildren.entrySet().stream()
                .filter(e -> e.getValue() == oldParent)
                .map(Map.Entry::getKey)
                .forEach(descendant -> parentsByChildren.put(descendant, newParent));
    }

    Stream<Set<ThreeDimensionalPoint>> circuitsBySizeDescending() {
        return parentsByChildren.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.toSet())))
                .values()
                .stream()
                .sorted((a, b) -> Integer.compare(b.size(), a.size()));
    }

    public List<PointPair> connectionsInOrderForCompleteCircuit() {
        return pairsByLengthAscending().takeWhile(p -> multipleCircuitsExist())
                .peek(this::createConnectionFor)
                .toList();
    }
    
    private Stream<PointPair> pairsByLengthAscending() {
        return points.stream()
                .flatMap(p -> points.stream()
                        .filter(o -> !o.equals(p))
                        .map(o -> new PointPair(p, o)))
                .distinct()
                .sorted(Comparator.comparing(PointPair::distanceBetween, Double::compareTo));
    }
    
    private boolean multipleCircuitsExist() {
        var allParents = parentsByChildren.values();
        for (var parent : allParents) {
            if (allParents.stream().anyMatch(otherParent -> otherParent != parent)) {
                return true;
            }
        }
        return false;
    }
}
