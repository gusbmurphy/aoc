package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.helper.grid.GridDirection.*;

class FileGrid implements Grid<Character> {
    private final List<String> rowStrings;
    private final int width;
    private final int height;

    FileGrid(String path) {
        var file = new File(path);
        AtomicInteger longestLength = new AtomicInteger();
        rowStrings = LineReader
                .readAllLinesFrom(file.getAbsolutePath())
                .peek(s -> longestLength.set(Integer.max(longestLength.get(), s.length())))
                .toList();
        width = longestLength.get();
        height = rowStrings.size();
    }

    @Override
    public Stream<Line<Character>> rows() {
        return rowStrings.stream()
                .map(this::padString)
                .map(StringLine::of);
    }

    @Override
    public Stream<Line<Character>> columns() {
        return IntStream.range(0, width)
                .mapToObj(this::columnAt);
    }

    @Override
    public boolean containsPosition(GridPosition position) {
        return position.x() < width && position.y() < height;
    }

    private static final List<GridDirection> DIAGONAL_DIRECTIONS = List.of(NW, NE, SE, SW);

    private static boolean directionIsDiagonal(GridDirection direction) {
        return DIAGONAL_DIRECTIONS.contains(direction);
    }

    @Override
    public Line<Character> lineFromPositionHeading(GridPosition position, GridDirection direction) {
        if (directionIsDiagonal(direction)) {
            throw new IllegalArgumentException("No implementation for diagonal headings");
        }

        Supplier<Stream<Character>> streamSupplier = () -> Stream.iterate(
                        position, this::containsPosition, p -> p.toThe(direction)).map(this::charAt);

        return new StreamLine<>(streamSupplier);
    }

    @Override
    public Stream<GridPosition> positionsOf(Character character) {
        return IntStream.range(0, width)
                .boxed()
                .flatMap(x -> IntStream.range(0, height)
                        .mapToObj(y -> new GridPosition(x, y))
                        .filter(pos -> charAt(pos).equals(character)));
    }

    private Character charAt(GridPosition pos) {
        return rowStrings.get(pos.y()).charAt(pos.x());
    }

    private Line<Character> columnAt(int index) {
        return new StringLine(rows().map(r -> r.get(index).orElseThrow()).toList());
    }

    private String padString(String s) {
        var sb = new StringBuilder(s);
        while (sb.length() < width) {
            sb.append(" ");
        }
        return sb.toString();
    }
}
