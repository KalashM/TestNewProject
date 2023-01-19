package com.example.collections;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsUpdTest {

    @ParameterizedTest
    @MethodSource("integerListProvider")
    void removeDuplicatesTest(List<Integer[]> actual, List<Integer[]> expected) {
        List<Integer[]> actualToTest = CollectionsUpd.removeDuplicates(actual);
        for (int i = 0; i < actual.size(); i++) {
            assertArrayEquals(expected.get(i), actualToTest.get(i));
        }
    }

    @ParameterizedTest
    @MethodSource("integerArrayUnion")
    void arraysUnionTest(List<Integer[]> actual, Integer[] expected) {
        assertArrayEquals(expected, CollectionsUpd.arraysUnion(actual));
    }

    @ParameterizedTest
    @MethodSource("integerArrayIntersect")
    void arraysIntersectionTest(List<Integer[]> actual, Integer[] expected) {
        assertArrayEquals(expected, CollectionsUpd.arraysIntersection(actual));
    }

    @ParameterizedTest
    @MethodSource("integerArrayDifference")
    void arraysDifferenceTest(List<Integer[]> actual, Integer[] expected) {
        assertArrayEquals(expected, CollectionsUpd.arraysDifference(actual.get(0), actual.get(1)));
    }

    @ParameterizedTest
    @MethodSource("stringsFrequencyVocab")
    void frequencyDictionaryTrueTest(String actualString, TreeMap<Character, Long> expectedMap) {
        assertTrue(expectedMap.equals(CollectionsUpd.frequencyDictionary(actualString)));
    }

    public static Stream<Arguments> integerListProvider() {
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

    public static Stream<Arguments> integerArrayUnion() {
        return Stream.of(
                Arguments.of(new ArrayList<Integer[]>(
                                Arrays.asList(new Integer[]{1, 2, 3, 3, 4, 4},
                                        new Integer[]{1, 1, 5, 8, 5, 4},
                                        new Integer[]{1, 0, 0, 1})),
                             new Integer[]{0, 1, 2, 3, 4, 5, 8}));
    }

    public static Stream<Arguments> integerArrayIntersect() {
        return Stream.of(
                Arguments.of(new ArrayList<Integer[]>(
                                Arrays.asList(new Integer[]{1, 2, 3, 3, 4, 4},
                                        new Integer[]{1, 1, 5, 8, 5, 4},
                                        new Integer[]{1, 0, 0, 1})),
                                new Integer[]{1}),
                Arguments.of(new ArrayList<Integer[]>(
                                Arrays.asList(new Integer[]{0, 1, 2, 5, 10},
                                        new Integer[]{2, 22, 5, 8,},
                                        new Integer[]{1, 2, 5, 17,},
                                        new Integer[]{null})),
                                new Integer[]{}));
    }

    public static Stream<Arguments> integerArrayDifference() {
        return Stream.of(
                Arguments.of(new ArrayList<Integer[]>(
                                Arrays.asList(new Integer[]{1, 2, 3, 3, 4, 4},
                                        new Integer[]{1, 1, 5, 8, 5, 4})),
                             new Integer[]{2, 3}));
    }

    public static Stream<Arguments> stringsFrequencyVocab() {
        Map<Character, Long> expectedMap1 = new TreeMap<>();
        expectedMap1.put('1', 3L);
        expectedMap1.put('a', 2L);
        expectedMap1.put('i', 1L);
        expectedMap1.put('n', 1L);
        expectedMap1.put('r', 1L);
        expectedMap1.put('M', 1L);

        Map<Character, Long> expectedMap2 = new TreeMap<>();
        expectedMap2.put('0', 1L);
        expectedMap2.put('1', 4L);
        expectedMap2.put('4', 1L);
        expectedMap2.put('9', 2L);

        return Stream.of(Arguments.of("Marina111",expectedMap1),
                Arguments.of("14.10.1991 - МК",expectedMap2)
        );
    }

}