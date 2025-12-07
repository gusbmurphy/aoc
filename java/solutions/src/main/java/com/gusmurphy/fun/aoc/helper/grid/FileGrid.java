package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class FileGrid implements Grid<Character> {
    private List<String> rowStrings;
    private int width;

    FileGrid(String path) {
        var file = new File(path);
        AtomicInteger longestLength = new AtomicInteger();
        rowStrings = LineReader
                .readAllLinesFrom(file.getAbsolutePath())
                .peek(s -> longestLength.set(Integer.max(longestLength.get(), s.length())))
                .toList();
        width = longestLength.get();
    }

    @Override
    public Stream<Line<Character>> rows() {
        return rowStrings.stream()
                .map(this::padString)
                .map(Line::of);
    }

    @Override
    public Stream<Line<Character>> columns() {
        return IntStream.range(0, width)
                .mapToObj(this::columnAt);
    }

    private Line<Character> columnAt(int index) {
        return new Line<>(rows().map(r -> r.get(index).orElseThrow()).toList());
    }

    private String padString(String s) {
        var sb = new StringBuilder(s);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
