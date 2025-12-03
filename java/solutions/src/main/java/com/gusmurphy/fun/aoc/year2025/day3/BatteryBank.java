package com.gusmurphy.fun.aoc.year2025.day3;

import java.util.Objects;

public class BatteryBank {
    private final String string;

    public BatteryBank(String string) {
        this.string = string;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BatteryBank that = (BatteryBank) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }
}
