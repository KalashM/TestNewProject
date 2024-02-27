package com.example.classloader.implement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculateSquareRootTest {

    @ParameterizedTest
    @MethodSource("parameters")
    void getResult(ArrayList<Double> parametersList, Double expected) {
        Double actual = new CalculateSquareRoot(parametersList).getResult();
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void getParametersCount(ArrayList<Double> parametersList, Double sum, int expected) {
        int actual = new CalculateSquareRoot(parametersList).getParametersCount();
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void getDescription(ArrayList<Double> parametersList) {
        String actual = new CalculateSquareRoot(parametersList).getDescription();
        assertEquals(actual, "The result of a square root of the first parameter");
    }

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(new Double[]{1.0, 2.0, 3.5})), 1.0, 3),
                Arguments.of(new ArrayList<>(List.of(new Double[]{4.0, 1.0})), 2.0, 2),
                Arguments.of(new ArrayList<>(List.of(new Double[]{9.0})), 3.0, 1)
        );
    }
}