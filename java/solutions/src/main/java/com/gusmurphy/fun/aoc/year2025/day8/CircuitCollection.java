package com.gusmurphy.fun.aoc.year2025.day8;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CircuitCollection {
    private final Map<ThreeDimensionalPoint, ThreeDimensionalPoint> parentsByChildren;

    CircuitCollection(List<ThreeDimensionalPoint> points) {
        parentsByChildren = points.stream()
                .collect(Collectors.toMap(p -> p, p -> p));
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
}
