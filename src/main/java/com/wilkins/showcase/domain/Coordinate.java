package com.wilkins.showcase.domain;

public record Coordinate(double x, double y) {
    public static Coordinate of(double x, double y) {
        return new Coordinate(x, y);
    }
}
