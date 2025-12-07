package com.gusmurphy.fun.aoc.helper.grid;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

class StreamLine<T> implements Line<T> {
    private final Supplier<Stream<T>> streamSupplier;
    
    StreamLine(Supplier<Stream<T>> streamSupplier) {
        this.streamSupplier = streamSupplier;
    }
    
    @Override
    public Optional<T> get(int i) {
        if (i == 0) {
            return streamSupplier.get().findFirst();
        }
        
        return streamSupplier.get().skip(i - 1).findFirst();
    }

    @Override
    public Stream<T> elements() {
        return streamSupplier.get();
    }
}
