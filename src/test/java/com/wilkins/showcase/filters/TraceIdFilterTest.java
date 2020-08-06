package com.wilkins.showcase.filters;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

class TraceIdFilterTest {

    @Test
    public void foo() {
        var something = true;
        assertThat(something, is(true));
    }
}