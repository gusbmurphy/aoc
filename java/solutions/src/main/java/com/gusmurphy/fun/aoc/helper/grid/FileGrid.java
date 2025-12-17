package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

class FileGrid<E> extends BaseGrid<E> {
    FileGrid(String path, Function<Character, E> characterConverter, E emptyValue) {
        super(getList(path, characterConverter, emptyValue));
    }

    private static <E> List<List<E>> getList(String path, Function<Character, E> characterConverter, E emptyValue) {
        var file = new File(path);
        AtomicInteger longestLength = new AtomicInteger();
        var rowsBeforePadding = LineReader
                .readAllLinesFrom(file.getAbsolutePath())
                .peek(s -> longestLength.set(Integer.max(longestLength.get(), s.length())))
                .map(s -> convertStringToListOfElements(characterConverter, s))
                .toList();
        var width = longestLength.get();
        return rowsBeforePadding.stream().map(l -> padListWithValue(l, emptyValue, width)).toList();
    }

    private static <E> List<E> convertStringToListOfElements(Function<Character, E> characterConverter, String s) {
        return s.chars().mapToObj(i -> characterConverter.apply((char) i)).toList();
    }

    private static <E> List<E> padListWithValue(List<E> list, E value, int width) {
        if (list.size() == width) {
            return list;
        }

        var paddedList = new ArrayList<>(list);
        while (paddedList.size() < width) {
            paddedList.add(value);
        }
        return paddedList;
    }
}
