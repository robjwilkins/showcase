package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Coordinate;

public class DefaultLineService implements LineService {
    @Override
    public double length(Coordinate start, Coordinate end) {
        var adjacent = start.x() - end.x();
        var opposite = start.y() - end.y();
        return Math.hypot(adjacent, opposite);
    }
}
