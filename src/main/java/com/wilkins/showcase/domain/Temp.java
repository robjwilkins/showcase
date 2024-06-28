package com.wilkins.showcase.domain;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.DAYS;

public class Temp implements Party {
    @Override
    public Duration noticePeriod() {
        return Duration.of(2, DAYS);
    }
}
