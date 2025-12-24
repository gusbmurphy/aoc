package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection;
import com.gusmurphy.fun.aoc.helper.geometry.IntegerPoint;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.gusmurphy.fun.aoc.helper.geometry.CardinalDirection.*;

public abstract class BaseGrid<E> implements Grid<E> {
    private static final List<CardinalDirection> DIAGONAL_DIRECTIONS = List.of(NW, NE, SE, SW);
    protected final List<List<E>> listRows;
    protected final int width;
    protected final int height;

    public BaseGrid(List<List<E>> listRows) {
        this.listRows = listRows;
        this.width = listRows.getFirst().size();
        height = listRows.size();
    }

    private static boolean directionIsDiagonal(CardinalDirection direction) {
        return DIAGONAL_DIRECTIONS.contains(direction);
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
    public boolean containsPosition(IntegerPoint position) {
        return position.x() < width && position.y() < height;
    }

    @Override
    public Line<E> lineFromPositionHeading(IntegerPoint position, CardinalDirection direction) {
        if (BaseGrid.directionIsDiagonal(direction)) {
            throw new IllegalArgumentException("No implementation for diagonal headings");
        }

        Supplier<Stream<E>> streamSupplier = () -> Stream.iterate(
                position, this::containsPosition, p -> p.toThe(direction)).map(this::elementAt);

        return new StreamLine<>(streamSupplier);
    }

    @Override
    public Stream<IntegerPoint> positionsOf(E character) {
        return IntStream.range(0, width)
                .boxed()
                .flatMap(x -> IntStream.range(0, height)
                        .mapToObj(y -> new IntegerPoint(x, y))
                        .filter(pos -> elementAt(pos).equals(character)));
    }

    private E elementAt(IntegerPoint pos) {
        return listRows.get(pos.y()).get(pos.x());
    }

    private Line<E> columnAt(int index) {
        return new StreamLine<>(() -> yIndexes().mapToObj(y -> listRows.get(y).get(index)));
    }

    private IntStream yIndexes() {
        return IntStream.range(0, height);
    }
}
