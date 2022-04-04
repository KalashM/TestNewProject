package com.example.collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsTest {

    @ParameterizedTest
    @MethodSource("stringArrayProvider")
    void removeDuplicatesTest(List<Integer[]> actual, List<Integer[]> expected) {
        List<Integer[]> actualToTest = Collections.removeDuplicates(actual);
        for (int i = 0; i < actual.size(); i++) {
            assertArrayEquals(expected.get(i), actualToTest.get(i));
        }
    }

    public static Stream<Arguments> stringArrayProvider() {
        return Stream.of(
                Arguments.of(new ArrayList<Integer[]>(
                                Arrays.asList(new Integer[]{1, 2, 3, 3, 4, 4},
                                        new Integer[]{1, 1, 5, 8, 5, 4},
                                        new Integer[]{1, 0, 0, 1})
                                    ),
                            new ArrayList<Integer[]>(
                                Arrays.asList(new Integer[]{1, 2, 3, 4},
                                        new Integer[]{1, 4, 5, 8},
                                        new Integer[]{0, 1})
                                    )));
    }

}