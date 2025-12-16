package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.helper.grid.GridDirection.*;

class FileGrid<E> implements Grid<E> {
    private final List<List<E>> listRows;
    private final int width;
    private final int height;
    private final E emptyValue;

    FileGrid(String path, Function<Character, E> characterConverter, E emptyValue) {
        this.emptyValue = emptyValue;

        var file = new File(path);
        AtomicInteger longestLength = new AtomicInteger();
        var rowsBeforePadding = LineReader
                .readAllLinesFrom(file.getAbsolutePath())
                .peek(s -> longestLength.set(Integer.max(longestLength.get(), s.length())))
                .map(s -> convertStringToListOfElements(characterConverter, s))
                .toList();
        width = longestLength.get();
        listRows = rowsBeforePadding.stream().map(this::padListWithEmptyValue).toList();
        height = listRows.size();
    }

    private static <E> List<E> convertStringToListOfElements(Function<Character, E> characterConverter, String s) {
        return s.chars().mapToObj(i -> characterConverter.apply((char) i)).toList();
    }

    @Override
    public Stream<Line<E>> rows() {
        return listRows.stream().map(r -> new StreamLine<>(r::stream));
    }

    @Override
    public Stream<Line<E>> columns() {
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
    public Line<E> lineFromPositionHeading(GridPosition position, GridDirection direction) {
        if (directionIsDiagonal(direction)) {
            throw new IllegalArgumentException("No implementation for diagonal headings");
        }

        Supplier<Stream<E>> streamSupplier = () -> Stream.iterate(
                position, this::containsPosition, p -> p.toThe(direction)).map(this::elementAt);

        return new StreamLine<>(streamSupplier);
    }

    @Override
    public Stream<GridPosition> positionsOf(E character) {
        return IntStream.range(0, width)
                .boxed()
                .flatMap(x -> IntStream.range(0, height)
                        .mapToObj(y -> new GridPosition(x, y))
                        .filter(pos -> elementAt(pos).equals(character)));
    }

    private E elementAt(GridPosition pos) {
        return listRows.get(pos.y()).get(pos.x());
    }

    private Line<E> columnAt(int index) {
        return new StreamLine<>(() -> yIndexes().mapToObj(y -> listRows.get(y).get(index)));
    }

    private IntStream xIndexes() {
        return IntStream.range(0, width);
    }

    private IntStream yIndexes() {
        return IntStream.range(0, height);
    }

    private List<E> padListWithEmptyValue(List<E> list) {
        if (list.size() == width) {
            return list;
        }

        var paddedList = new ArrayList<>(list);
        while (paddedList.size() < width) {
            paddedList.add(emptyValue);
        }
        return paddedList;
    }
}
