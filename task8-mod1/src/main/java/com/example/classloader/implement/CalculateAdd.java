package com.example.classloader.implement;

import com.example.classloader.Calculable;

import java.util.ArrayList;

public class CalculateAdd implements Calculable {
    private final String description = "Sum of all parameters";

    private ArrayList<Double> parametersList;

    public CalculateAdd() {
    }

    public CalculateAdd(ArrayList<Double> parametersList) {
        this.parametersList = parametersList;
    }

    public void setParametersList(ArrayList<Double> parametersList) {
        this.parametersList = parametersList;
    }

    @Override
    public double getResult() {
        double resultSum = 0;
        if (!parametersList.isEmpty()) {
            for (Double parameter: parametersList) {
               resultSum = resultSum + parameter;
            }
        }
        return resultSum;
    }

    @Override
    public int getParametersCount() {
        return this.parametersList.size();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}