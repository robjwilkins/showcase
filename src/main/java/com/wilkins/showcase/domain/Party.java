package com.wilkins.showcase.domain;

import java.time.Duration;

public interface Party {

    Duration noticePeriod();

    //    void accept(Visitor v);
    default String accept(Visitor v) {
        return v.visit(this);
    }
}
