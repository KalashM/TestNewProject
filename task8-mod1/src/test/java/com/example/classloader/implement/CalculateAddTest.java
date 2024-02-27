package com.example.classloader.implement;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculateAddTest {

    @ParameterizedTest
    @MethodSource("parameters")
    void getResult(ArrayList<Double> parametersList, Double expected) {
        Double actual = new CalculateAdd(parametersList).getResult();
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void getParametersCount(ArrayList<Double> parametersList, Double sum, int expected) {
        int actual = new CalculateAdd(parametersList).getParametersCount();
        assertEquals(actual, expected);
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void getDescription(ArrayList<Double> parametersList) {
        String actual = new CalculateAdd(parametersList).getDescription();
        assertEquals(actual, "Sum of all parameters");
    }

    public static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(new ArrayList<>(List.of(new Double[]{1.0, 2.0, 3.5})), 6.5, 3),
                Arguments.of(new ArrayList<>(List.of(new Double[]{5.2, 1.0})), 6.2, 2),
                Arguments.of(new ArrayList<>(List.of(new Double[]{1.0})), 1.0, 1)
                );
    }

}