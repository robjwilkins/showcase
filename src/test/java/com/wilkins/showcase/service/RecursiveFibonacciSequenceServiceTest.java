package com.wilkins.showcase.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RecursiveFibonacciSequenceServiceTest {

    @Test
    void canCreateSequenceWithNineEntries() {
        var underTest = new RecursiveFibonacciSequenceService();
        assertThat(underTest.getSequenceWithSize(9)).isEqualTo(List.of(1, 1, 2, 3, 5, 8, 13, 21, 34));
    }
}