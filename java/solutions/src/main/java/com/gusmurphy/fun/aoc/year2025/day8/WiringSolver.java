package com.gusmurphy.fun.aoc.year2025.day8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class WiringSolver {
    public static long productOfThreeLargestCircuits(String path, int maxNumberOfConnections) {
        var points = ThreeDimensionalPointListParser.parseFile(path).toList();
        List<Circuit> circuits = new ArrayList<>();
        points.stream()
                .flatMap(p -> points.stream()
                        .filter(o -> !o.equals(p))
                        .map(o -> new PointPair(p, o)))
                .distinct()
                .sorted(Comparator.comparing(PointPair::distanceBetween, Double::compareTo))
                .limit(maxNumberOfConnections)
                .forEach(pair -> {
                    var circuitContainingOne = circuits.stream()
                            .filter(c -> c.includes(pair.a) || c.includes(pair.b))
                            .findFirst();
                    
                    if (circuitContainingOne.isPresent()) {
                        var c = circuitContainingOne.get();
                        if (c.includes(pair.a)) {
                            c.add(pair.b);
                        } else {
                            c.add(pair.a);
                        }
                    } else {
                        circuits.add(new Circuit(List.of(pair.a, pair.b)));
                    }
                });
        
        return circuits.stream()
                .map(Circuit::pointCount)
                .sorted((a, b) -> Long.compare(b, a))
                .limit(3)
                .reduce(1L, (a, b) -> a * b);
    }
    
    private static class PointPair {
        private final ThreeDimensionalPoint a;
        private final ThreeDimensionalPoint b;
        
        PointPair(ThreeDimensionalPoint a, ThreeDimensionalPoint b) {
            var sorted = Stream.of(a, b).sorted(Comparator.comparing(ThreeDimensionalPoint::x)).toList();
            this.a = sorted.getFirst();
            this.b = sorted.getLast();
        }
        
        double distanceBetween() {
            return a.distanceTo(b);
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            PointPair pointPair = (PointPair) o;
            return Objects.equals(a, pointPair.a) && Objects.equals(b, pointPair.b);
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
    
    private static class Circuit {
        List<ThreeDimensionalPoint> points;
        
        Circuit(List<ThreeDimensionalPoint> points) {
            this.points = new ArrayList<>(points);
        }
        
        boolean includes(ThreeDimensionalPoint point) {
            return points.contains(point);
        }
        
        void add(ThreeDimensionalPoint point) {
            points.add(point);
        }
        
        long pointCount() {
            return points.size();
        }
    }
}
