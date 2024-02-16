package com.example.classloader.implement;

import com.example.classloader.Calculable;

import java.util.ArrayList;

public class CalculateMultiply implements Calculable {
    private final String description = "The multiplication of all parameters";

    private ArrayList<Double> parametersList;

    public CalculateMultiply(ArrayList<Double> parametersList) {
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
            result = parametersList.get(0);
            if (parametersList.size() > 1) {
                for (int i = 1; i < parametersList.size(); i++) {
                    result = result * parametersList.get(i);
                }
            }
        }
        return result;
    }
}
