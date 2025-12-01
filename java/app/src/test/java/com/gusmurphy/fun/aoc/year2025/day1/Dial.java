package com.gusmurphy.fun.aoc.year2025.day1;

public class Dial {
    private final int position;

    public Dial() { this.position = 50; }
    private Dial(int position) { this.position = position; }

    public int position() { return position; }

    public Dial rotate(DialRotation rotation) {
        return new Dial(this.position + rotation.distance());
    }
}
