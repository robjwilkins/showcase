package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Coordinate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DefaultLineServiceTest {

    @Test
    void hypotenuseWhenTwoAndThree() {
        var underTest = new DefaultLineService();
        assertThat(underTest.length(Coordinate.of(0, 0), Coordinate.of(15, 20))).isEqualTo(25);
    }
}