package com.example.strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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

    private static Object[] reverseValues() {
        return new Object[]{
                new Object[]{"abc", "cba"},
                new Object[]{"", ""}
        };
    }
}