package com.example.arrays;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ArrayBlockCopyTest {

    @ParameterizedTest
    @MethodSource("intIntArrayIntProvider")
    public void testArrayBlockCopyMultiArgs(int from, int to, int[] expected) {
        int[] actual = new int[]{1, 2, 3, 4, 5};
        assertArrayEquals(expected, ArrayBlockCopy.arrayBlockCopy(actual, from, to));
    }

    @ParameterizedTest
    @MethodSource("intIntArrayIntProvider")
    public void testArrayBlockCopyUsingArrayMultiArgs(int from, int to, int[] expected) {
        int[] actual = new int[]{1, 2, 3, 4, 5};
        assertArrayEquals(expected, ArrayBlockCopy.arrayBlockCopyUsingArray(actual, from, to));
    }

    @ParameterizedTest
    @MethodSource("intIntArrayIntProvider")
    public void testArrayBlockCopyUsingListMultiArgs(int from, int to, int[] expected) {
        int[] actual = new int[]{1, 2, 3, 4, 5};
        assertArrayEquals(expected, ArrayBlockCopy.arrayBlockCopyUsingList(actual, from, to));
    }

    public static Stream<Arguments> intIntArrayIntProvider() {
        return Stream.of(
                Arguments.of(0, 2, new int[]{1, 2}),
                Arguments.of(4, 5, new int[]{5}),
                Arguments.of(3, 5, new int[]{4, 5})
        );
    }
}