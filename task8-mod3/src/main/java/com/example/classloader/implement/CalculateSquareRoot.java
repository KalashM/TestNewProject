package com.example.classloader.implement;

import com.example.classloader.Calculable;

import java.util.ArrayList;

public class CalculateSquareRoot implements Calculable {
    private final String description = "The result of a square root of the first parameter";

    private ArrayList<Double> parametersList;

    public CalculateSquareRoot(ArrayList<Double> parametersList) {
        this.parametersList = parametersList;
    }

    @Override
    public int getParametersCount() {
        return this.parametersList.size();
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public double getResult() {
        double result = 0;
        if (!parametersList.isEmpty()) {
            result = Math.sqrt(parametersList.get(0));
        }
        return result;
    }
}
