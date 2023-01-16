package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Coordinate;

public interface LineService {
    double length(Coordinate start, Coordinate end);
}
