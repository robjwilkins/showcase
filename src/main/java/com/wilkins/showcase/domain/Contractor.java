package com.wilkins.showcase.domain;

import java.time.Duration;

import static java.time.temporal.ChronoUnit.DAYS;

public class Contractor implements Party {
    @Override
    public Duration noticePeriod() {
        return Duration.of(7, DAYS);
    }

    @Override
    public String accept(Visitor v) {
        return v.visit(this);
    }
}
