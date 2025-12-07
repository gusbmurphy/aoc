package com.gusmurphy.fun.aoc.helper.grid;

import com.gusmurphy.fun.aoc.helper.LineReader;

import java.io.File;
import java.util.stream.Stream;

class FileGrid implements Grid<Character> {
    private final File file;
    
    FileGrid(String path) {
        file = new File(path);
    }
    
    @Override
    public Stream<Row<Character>> rows() {
        return LineReader.readAllLinesFrom(file.getAbsolutePath())
                .map(Row::of);
    }
}
