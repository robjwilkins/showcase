package com.wilkins.showcase.domain;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.DAYS;

public class Perm implements Party {
    @Override
    public Duration noticePeriod() {
        return Duration.of(28, DAYS);
    }
}
