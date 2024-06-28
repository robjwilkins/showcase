package com.wilkins.showcase.service;

import java.util.List;
import java.util.stream.IntStream;

public class RecursiveFibonacciSequenceService implements SequenceService {
    @Override
    public List<Integer> getSequenceWithSize(int size) {
        return IntStream.range(1, size + 1)
                .map(this::fib).boxed()
                .toList();
    }

    private int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
