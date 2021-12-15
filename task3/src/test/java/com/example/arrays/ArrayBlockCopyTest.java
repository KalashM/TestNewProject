package com.example.arrays;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayBlockCopyTest {

    @Test
    public void testArrayBlockCopyIntoNew() {
        int[] expected = new int[]{1, 2};
        assertArrayEquals(expected, ArrayBlockCopy.arrayBlockCopy(new int[]{1, 2, 3, 4}, 0, 2));
    }

    @Test
    public void testArrayBlockCopyIntoBeginning() {
        int[] expected = new int[]{5, 8, 3, 5, 8};
        int[] actual = new int[]{1, 2, 3, 5, 8};
        ArrayBlockCopy.arrayBlockCopy(actual, actual, 3, 5);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testArrayBlockCopyIntoPosition() {
        int[] actual = new int[]{1, 2, 3, 5, 8};
        int[] expected = new int[]{1, 2, 3, 1, 2};
        ArrayBlockCopy.arrayBlockCopy(actual, actual, 0, 2, 3);
        assertArrayEquals(expected, actual);
    }
}