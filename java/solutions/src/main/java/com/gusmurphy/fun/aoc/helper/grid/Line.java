package com.gusmurphy.fun.aoc.helper.grid;

import java.util.Optional;
import java.util.stream.Stream;

public interface Line<T> {
    Optional<T> get(int i);
    Stream<T> elements();
}
