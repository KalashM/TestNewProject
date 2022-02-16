package com.example.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

    @ParameterizedTest
    @MethodSource("reverseValues")
    void stringReverseUsingBuildInMethodTest(String actual, String expected) {
        Strings s = new Strings();
        assertEquals(expected, s.stringReverseUsingBuildInMethod(actual));
    }

    @Test
    public void stringReverseTestNullException() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            Strings s = new Strings();
            s.stringReverse(null);
        });

        String expectedMessage = "The input string is empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @MethodSource("reverseValues")
    void stringReverseTest(String actual, String expected) {
        Strings s = new Strings();
        assertEquals(expected, s.stringReverseUsingBuildInMethod(actual));
    }

    @ParameterizedTest
    @MethodSource("stringArrayProvider")
    void findCommonSymbolsTest(String[] actual, char[] expected) {
        assertArrayEquals(expected, Strings.findCommonSymbols(actual));
    }

    private static Object[] reverseValues() {
        return new Object[]{
                new Object[]{"1abc:", ":cba1"},
                new Object[]{"goal", "laog"},
                new Object[]{"", ""}
        };
    }

    public static Stream<Arguments> stringArrayProvider() {
        return Stream.of(
                Arguments.of(new String[]{"abbcd", "bcd", "abgt"}, new char[]{'b'}),
                Arguments.of(new String[]{"abbcd", "bcd", "tabd"}, new char[]{'b', 'd'}),
                Arguments.of(new String[]{"abbcd", "bcd", ""}, new char[]{}),
                Arguments.of(new String[]{"abbcd", "dab", "bad"}, new char[]{'a', 'b', 'd'}),
                Arguments.of(new String[]{"abbcd1", "bcd1", "tabd1"}, new char[]{'1', 'b', 'd'})
        );
    }
}