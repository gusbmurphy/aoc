package com.gusmurphy.fun.aoc.year2025.day6;

import java.util.List;

public record HomeworkProblem(Operation operation, List<Long> arguments) {
    public long solve() {
        var argStream = arguments.stream()
                .mapToLong(Long::valueOf);

        if (operation == Operation.ADD) {
            return argStream.reduce(0, Long::sum);
        }

        return argStream.reduce(1, (a, b) -> a * b);
    }
}
